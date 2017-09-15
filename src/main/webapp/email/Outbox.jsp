<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML >
<html>
<head>
<meta charset="utf-8">
<title>邮件管理--发送邮件</title>
<script
	src="${pageContext.request.contextPath }/resources/js/jquery-2.2.2.js"></script>


</head>

<body>


	<form action="Outbox" method="post" name="from1" id="uploadForm"
		enctype="multipart/form-data">
		<input type="hidden" name="fromId" id="fromId"
			value="${sessionScope['NEWER_USER_LOGIN_INFO'].userTrueName}">
		<table border="1">
			<tr>
				<td colspan="2">收件人：<input name="toId" id="toId" type="text"
					size="40px" ${priMailTo.priMail.fromId}>
				</td>
				<td>发送方式：<select name="copy" id="copy">
						<option value="抄送">抄送</option>
						<option value="密送" selected="selected">密送</option>
				</select></td>
				<td>重要程度：<select name="important">
						<option value="一般">一般</option>
						<option value="重要" selected="selected">重要</option>
						<option value="非常重要">非常重要</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="4">主题：<input type="text" name="subject"></td>
			</tr>
			<tr>
				<td colspan="4">附件：<input type="text" id="name" name="name">
					<div id="uploader-email">
						<!--用来存放item-->
						<div id="fileList_email" class="uploader-list"></div>
						<div id="filePicker_email" style="float: left;">选择文件</div>
						<button type="button" id="btnUpload_email">上传</button>
					</div>
					<a href="#" onclick="Show_Hidden(tab)">通讯录好友</a>
				</td>
			</tr>
			<tr>
				<td colspan="3" rowspan="30"><textarea rows="20" cols="73"
						name="content"></textarea></td>
				<td>
					<table id="tab" style="display: none;"></table>

				</td>
			</tr>
		</table>
		<input type="submit" value="提交" id="but" onclick="addOut.add('but');">
		<input type="submit" value="草稿箱" id="sub" onclick="addOut.add('sub');">

	</form>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/webuploader/webuploader.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/webuploader/webuploader.js"></script>


	<script type="text/javascript">

		$(function() {
                /*init webuploader*/
                var $list_email=$("#fileList_email");   //这几个初始化全局的百度文档上没说明，好蛋疼。
                var $btn_email =$("#btnUpload_email");   //开始上传
                var thumbnailWidth = 100;   //缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档
                var thumbnailHeight = 100;

                var uploader_email = WebUploader.create({
                    // 选完文件后，是否自动上传。
                    auto: false,
                    // swf文件路径
                    swf: '../webuploader/Uploader.swf',

                    // 文件接收服务端。
                    server: 'upload_picture',

                    // 选择文件的按钮。可选。
                    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                    pick: '#filePicker_email',

                    // 只允许选择图片文件。
                    /*accept: {
                        title: 'Images',
                        extensions: 'gif,jpg,jpeg,bmp,png',
                        mimeTypes: 'image/!*'
                    },*/
                    method:'POST',
                });
                // 当有文件添加进来的时候
                uploader_email.on( 'fileQueued', function( file ) {  // webuploader事件.当选择文件后，文件被加载到文件队列中，触发该事件。等效于 uploader.onFileueued = function(file){...} ，类似js的事件定义。
                    var $li = $(
                        '<div id="' + file.id + '" class="file-item thumbnail">' +
                        '<img>' +
                        '<div class="info">' + file.name + '</div>' +
                        '</div>'
                        ),
                        $img = $li.find('img');


                    // $list为容器jQuery实例
                    $list_email.append( $li );

                    // 创建缩略图
                    // 如果为非图片文件，可以不用调用此方法。
                    // thumbnailWidth x thumbnailHeight 为 100 x 100
                    uploader_email.makeThumb( file, function( error, src ) {   //webuploader方法
                        if ( error ) {
                            $img.replaceWith('<span>不能预览</span>');
                            return;
                        }

                        $img.attr( 'src', src );
                    }, thumbnailWidth, thumbnailHeight );
                });
                // 文件上传过程中创建进度条实时显示。
                uploader_email.on( 'uploadProgress', function( file, percentage ) {
                    var $li = $( '#'+file.id ),
                        $percent = $li.find('.progress span');

                    // 避免重复创建
                    if ( !$percent.length ) {
                        $percent = $('<p class="progress"><span></span></p>')
                            .appendTo( $li )
                            .find('span');
                    }

                    $percent.css( 'width', percentage * 100 + '%' );
                });

                // 文件上传成功，给item添加成功class, 用样式标记上传成功。
                uploader_email.on( 'uploadSuccess', function( file ) {
                    $("#name").val(""+file.name);
                    $( '#'+file.id ).addClass('upload-state-done');
                });

                // 文件上传失败，显示上传出错。
                uploader_email.on( 'uploadError', function( file ) {
                    var $li = $( '#'+file.id ),
                        $error = $li.find('div.error');

                    // 避免重复创建
                    if ( !$error.length ) {
                        $error = $('<div class="error"></div>').appendTo( $li );
                    }

                    $error.text('上传失败');
                });

                // 完成上传完了，成功或者失败，先删除进度条。
                uploader_email.on( 'uploadComplete', function( file ) {
                    $( '#'+file.id ).find('.progress').remove();
                });
                $btn_email.on( 'click', function() {
                    console.log("上传...");
                    uploader_email.upload();
                    console.log("上传成功");
                });

            /* 提交或者存入草稿箱 */
			addOut = {
				add : function(state) {
					if (state == 'but') {
						url = "Outbox?sendFlag=1";
					} else if (state == 'sub') {
						url = "Outbox?sendFlag=0";
					}
					$("#uploadForm").form({
						url : url,
						success : function(data) {
							if (data) {
								$.messager.show({
									title : "提示",
									msg : data
								});
							}
						}
					});

				}
			}
		});

		/*function checkFile() {

			$.ajax({
				url : 'upload.do',
				type : 'POST',
				cache : false,
				data : new FormData($('#uploadForm')[0]),
				processData : false,
				contentType : false
			}).done(function(res) {
			}).fail(function(res) {
			});

			var file = document.getElementById("file").files[0];
			console.log(file);
			document.getElementById('name').value = file['name'];

		}*/
	</script>


	<script type="text/javascript">
		function Show_Hidden(trid) {
			if (trid.style.display == "block") {
				trid.style.display = 'none';
			} else {

				trid.style.display = 'block';
			}
		}

		$(function() {
			var name = $("#fromId").val();

			$.ajax({
				type : "post",
				url : "findList",
				success : function(data) {
					if (data) {
						$.each(data, function() {
							var tr = $("<tr></tr>");
							$("#tab").append(tr);
							var td = "<td id="+this.userTrueName+"><a href='#' onclick=\"inser(" + "'"
									+ this.userTrueName + "'" + ")\">"
									+ this.userTrueName + "</a></td>";
							tr.append(td);
						});
						$("#"+name).remove();
					}
					
				}
			});

		})

		function inser(email) {
			var s = $("#toId").val();
			var copy = $("#copy").val();
			
				if (copy == '抄送') {
					arrS = s.split(",");
					flag = false; //标记是否已经添加
					for (i = 0; i < arrS.length; i++) {
						if (arrS[i] == email) { //判断该Email地址是否已经添加
							flag = true;
							break;
						}
					}
					if (!flag) {
						$("#toId").val(s + email + ",");
					}
				} else {
					$("#toId").val(email);
				}
			

		}
	</script>
</body>
</html>
