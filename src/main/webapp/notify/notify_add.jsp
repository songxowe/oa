<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>公告</title>
    <meta charset="utf-8">
</head>

<script src="${pageContext.request.contextPath }/resources/js/jquery-3.1.1.min.js"></script>


<body>
<div style="width:600px;margin:auto">
    <center><h2>新增公告</h2>
        <div id="notifyDialog">
            <table>
                <form id="uploadForm" name="f" enctype="multipart/form-data" class="form-inline" role="form">
                    <tr>
                        <td>公告标题：</td>
                        <td><input type="text" name="subject" id="subjectt"></td>
                    </tr>
                    <tr>
                        <td>公告内容：</td>
                        <td><textarea rows="10" cols="50" name="content" id="content"></textarea></td>
                    </tr>
                    <tr>
                        <td>附件：</td>
                        <td>
                            <%--<input type="file" name="attachment" id="file" onchange="checkFile()">--%>
                                <div id="uploader-notify">
                                    <!--用来存放item-->
                                    <div id="fileList_notify" class="uploader-list"></div>
                                    <div id="filePicker_notify" style="float: left;">选择文件</div>
                                    <button type="button" id="btnUpload_notify">上传</button>
                                </div>
                            <input type="hidden" id="name" name="name">
                            <input type="hidden" id="attname">
                        </td>
                    </tr>
                    <tr>
                        <td cospan="2">
                            <button type="button" class="btn btn-primary btn-lg btn-block" onclick="addNewNotify()">添加
                            </button>
                        </td>
                    </tr>
                </form>
            </table>
        </div>
    </center>
</div>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/webuploader/webuploader.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/webuploader/webuploader.js"></script>
<script type="text/javascript">
    $(function() {
        /*init webuploader*/
        var $list_notify=$("#fileList_notify");   //这几个初始化全局的百度文档上没说明，好蛋疼。
        var $btn_notify =$("#btnUpload_notify");   //开始上传
        var thumbnailWidth = 100;   //缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档
        var thumbnailHeight = 100;

        var uploader_notify = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: false,
            // swf文件路径
            swf: '../webuploader/Uploader.swf',

            // 文件接收服务端。
            server: 'upload_picture',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker_notify',

            // 只允许选择图片文件。
            /*accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/!*'
            },*/
            method:'POST',
        });
        // 当有文件添加进来的时候
        uploader_notify.on( 'fileQueued', function( file ) {  // webuploader事件.当选择文件后，文件被加载到文件队列中，触发该事件。等效于 uploader.onFileueued = function(file){...} ，类似js的事件定义。
            var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '<div class="info">' + file.name + '</div>' +
                '</div>'
                ),
                $img = $li.find('img');


            // $list为容器jQuery实例
            $list_notify.append( $li );

            // 创建缩略图
            // 如果为非图片文件，可以不用调用此方法。
            // thumbnailWidth x thumbnailHeight 为 100 x 100
            uploader_notify.makeThumb( file, function( error, src ) {   //webuploader方法
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }

                $img.attr( 'src', src );
            }, thumbnailWidth, thumbnailHeight );
        });
        // 文件上传过程中创建进度条实时显示。
        uploader_notify.on( 'uploadProgress', function( file, percentage ) {
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
        uploader_notify.on( 'uploadSuccess', function( file ) {
            $("#attname").val(""+file.name);
            $( '#'+file.id ).addClass('upload-state-done');
        });

        // 文件上传失败，显示上传出错。
        uploader_notify.on( 'uploadError', function( file ) {
            var $li = $( '#'+file.id ),
                $error = $li.find('div.error');

            // 避免重复创建
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $li );
            }

            $error.text('上传失败');
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader_notify.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').remove();
        });
        $btn_notify.on( 'click', function() {
            console.log("上传...");
            uploader_notify.upload();
            console.log("上传成功");
        });
    })
/*    function checkFile() {
        $.ajax({
            url: 'upload.do',
            type: 'POST',
            cache: false,
            data: new FormData($('#uploadForm')[0]),
            processData: false,
            contentType: false,
            success: function (data) {
                alert(data)
                $("#attname").val(data)
            }
        }).done(function (res) {
        }).fail(function (res) {
        });


        var file = document.getElementById("file").files[0];
        console.log(file);


    }*/

    function addNewNotify() {
        var subject = $("#subjectt").val();
        var content = $("#content").val();
        var attachment = $("#attname").val();

        $.ajax({
            url: 'add_notify',
            type: 'post',
            dataType: 'json',
            data: {'subject': subject, 'content': content, 'attachment': attachment},
            success: function (data) {
                $.messager.show({
                    title: '系统消息',
                    msg: data + '条公告已发布',
                    timeout: 5000
                })
                $("#newNotify").window('close', true)

            }
        });
    }
</script>
</body>

</html>
