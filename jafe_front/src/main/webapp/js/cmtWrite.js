$(function () {
    var $wd = $(".js_wd"),
        $ztpj = $(".js_ztpj");
    // window.comment = {
    //     feel:[]
    // };
    var cmt = {
        can: false,
        textOk: false
    };

    //总体评价
    var coreFeel = {
        feelText:[
            "不太满意，有些失望",
            "感觉一般，还需改进",
            "还不错，我喜欢",
            "满意，我很喜欢"
        ],
        init: function () {
            var self = this;
            var $feelWrap = $('.cmt-score');
            var $feels = $feelWrap.find("b");
            self.tip = $feelWrap.find(".feel-tip");
            $feelWrap.on('click','b',function () {
                var $this = $(this),
                    $idx = $this.index();
                self.change($this);
                // window.comment.feel[4] = $idx;
                $ztpj.val($idx);
                checkForm();
            }).on("mouseenter","b",function () {
                var $this = $(this);
                clearTimeout(self.timer);
                self.change($this);
            }).on("mouseout","b",function () {
                var $this = $(this);
                self.timer = setTimeout(function(){
                    //if (window.comment.feel[4] != undefined) {
                    if ($ztpj.val() != "") {
                        //self.change($feels.eq(window.comment.feel[4]));
                        self.change($feels.eq($ztpj.val()));
                    } else {
                        $feels.removeClass("current l r");
                        self.tip.html("点击表情评分").removeClass("hot");
                    }
                },300);

            });
        },
        change: function (elem) {
            var self = this;
            elem.removeClass("l r").addClass("current").siblings().removeClass("current l r");
            elem.nextAll("b").addClass('l');
            elem.prevAll("b").addClass('r');
            self.tip.html(self.feelText[elem.index()]).addClass("hot");
        }
    };
    coreFeel.init();

    //小星星评分
    var pf = {
        tip:[
            "不太满意，有些失望",
            "感觉一般，还需改进",
            "还不错，我喜欢",
            "满意，我很喜欢"
        ],
        timer:null,
        init: function () {
            var self = this;
            $(".cmt-childlever").on("click",".comlevel-form b",function () {
                var $this = $(this);
                var $tip = $this.parent().next();
                self.df($this,$tip);
                //window.comment.feel[$this.parents("li").index()] = $this.index();
                $wd.eq($this.parents("li").index()).val($this.index());
                checkForm();
            }).on("mouseenter",".comlevel-form b", function () {
                var $this = $(this);
                var $par = $this.parent();
                var $tip = $par.next();
                $this.parents("li").index() == self.li && clearTimeout(self.timer);
                self.li = $this.parents("li").index();
                self.df($this,$tip);
            }).on("mouseout",".comlevel-form b", function() {
                var $this = $(this);
                var $tip = $this.parent().next();
                var $pdx = $this.parents("li").index();

                self.timer = setTimeout(function(){
                    //if (window.comment.feel[$pdx] != undefined) {
                    if ($wd.eq($pdx).val() != "") {
                        //self.df($this.parent().find("b").eq(window.comment.feel[$pdx]),$tip);
                        self.df($this.parent().find("b").eq($wd.eq($pdx).val()),$tip);
                    } else {
                        $this.removeClass("current").siblings().removeClass("current");
                        $tip.empty();
                    }
                },200);

            });
        },
        df: function (elem,tip) {
            elem.addClass("current").nextAll().removeClass("current");
            elem.prevAll().addClass("current");
            tip.text(this.tip[elem.index()]);
        }
    };
    pf.init();

    var con = {
        placeHolder: "亲，根据以上提示分享下出游体验吧，20～1000字……",
        area: $(".writeCmt-area"),
        tip: $('.cmtCon-tips'),
        init: function () {
            var self = this;
            self.area.on('keyup',function () {
                self.checkText();
                checkForm();
            }).on('focus',function () {
                self.filterText(self.area.val()) == self.placeHolder && self.area.val("");
            }).on("blur",function () {
                self.filterText(self.area.val()) == "" && self.area.val(self.placeHolder);
            });
        },
        getLength: function(str){
            //获取文字长度
            return String(str).replace(/[^\x00-\xff]/g,'aa').length;
        },
        filterText: function (text) {
            //过滤字符
            text = text.replace(/^\s+|\s+$/g,""); //去除首尾空格
            text = text.replace(/\s+/g, ''); // 去掉中间空格;
            return text;
        },
        checkText: function () {
            //输入框状态提示
            var self = this;
            var val = self.area.val().replace(/^\s+|\s+$/g,""); //去除首尾空格
            val = val.replace(/\s+/g, '&nbsp;'); // 空格 转成 &nbsp;
            val = val.replace(/\n/g, '<br />'); // 回车 \n 转成 <br />
            if(val == self.placeHolder) return false;
            var lencount = self.getLength(val);
            var len = parseInt(lencount/2),
                str = '';
            lencount <= 0 ? self.area.css("color","#ccc") : self.area.css("color","#666");
            if (len == 0) {
                str = '输入20-1000字';
                cmt.textOk = false;
            } else if(len < 20) {
                str = '还差 <dfn>' + (20 - len) + '</dfn> 个字就可以提交喽，加油';
                cmt.textOk = false;
            } else if((len < 200) && (len >= 20)) {
                str = '太棒了，再写<dfn>' + (200 - len) + '</dfn>个字有机会成为精华点评获得300积分'
                cmt.textOk = true;
            } else if(len > 1000) {
                str = '已经达到1000上限喽，不能再写啦，可以再次检查下。';
                cmt.textOk = false;
            } else {
                str = '你已写了<dfn>' + len + '</dfn>个字，有机会获得300积分';
                cmt.textOk = true;
            }
            self.tip.html(str);
        }
    };

    con.init();

    var $submit = $('.js_submit');

    //验证是否可提交
    function checkForm() {
        //var feel = window.comment.feel;
        //if (feel.length != 5) return false;
        // for (var i = 0; i < 4; i++) {
        //     if(feel[i] > 5 || feel[i] < 1 || typeof(feel[i]) != "number") {
        //         return false;
        //     }
        //     cmt.can = true;
        // }
        cmt.can = true;
        if($wd.length) {
            $wd.each(function () {
                if($(this).val() == "") {
                    cmt.can = false;
                    return false;
                }
            });
            $ztpj.val() == "" && (cmt.can = false);
        }

        if (cmt.textOk == true && cmt.can == true) {
            $submit.removeClass('btn-disabled');
        } else {
            $submit.addClass('btn-disabled');
            return false;
        }
        return true;
    }


    //展开demo
    $('.JS_opendemo').click(function(){
        createDemo();
    });
    function createDemo() {
        if($(document).find('.com-demos').length > 0) return false;
        var demo = "<div class=\"com-demos\"><i class=\"iconcom iconcom-democlose\"></i><span class=\"JS_demonext com-demonext\"><i class=\"iconcom iconcom-next\"></i></span><img src=\"http://pic.lvmama.com/img/v6/comment/com_demo1.png\"></div>";
        $('body').append(demo);
        var demoBox = $('.com-demos'),
            demoIndex = 1;
        $('.iconcom-democlose').click(function(){
            demoBox.remove();
        });
        $('.JS_demonext').click(function(){
            var me = $(this);
            demoIndex++;
            if (demoIndex == 3)  me.find('i').removeClass('iconcom-next').addClass('iconcom-demook');
            if(demoIndex > 3) {
                demoBox.remove();
            } else {
                demoBox.find('img').remove();
                demoBox.append('<img src="http://pic.lvmama.com/img/v6/comment/com_demo' + demoIndex + '.png">');
            }
        });
    }

});



//实例化一个plupload上传对象
var uploader = new plupload.Uploader({
        // runtimes : 'html5,flash,silverlight,html4',
        browse_button : 'pickfiles', //触发文件选择对话框的按钮，为那个元素id
        url : 'http://www.lvmama.com/comment/ajax/picture/uploadPicuture', //服务器端的上传页面地址
        flash_swf_url : 'http://pic.lvmama.com/js/v6/comment/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
        silverlight_xap_url : 'http://pic.lvmama.com/js/v6/comment/Moxie.xap', //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数

        filters : {
            max_file_size : '10mb',
            mime_types: [
                {title : "Image files", extensions : "jpg,gif,png,jpeg"}
            ]
        }
    }),
    $fileBox = $('.cmt-upload-img'), //图片盒子
    $cmtUpInf = $(".cmt-upInf");
//fileNum = uploader.total, //统计已上传个数

//在实例对象上调用init()方法进行初始化
uploader.init();

//绑定各种事件，并在事件监听函数中做你想做的事
uploader.bind('FilesAdded',function(uploader,files){
    //每个事件监听函数都会传入一些很有用的参数，
    var len = files.length,
        html = '',
        liLen = $fileBox.find('li').length;
    if ( liLen == 9 ) {
        nova.dialog({
            content: '您已传满九张',
            wrapClass:'commentFullAlert',
            okCallback: true
        });
        return false;
    } else if (len + liLen > 9) {
        nova.dialog({
            content: '数量超过九张，请重新选择',
            wrapClass:'commentFullAlert',
            okCallback: true
        });
        return false;
    }

    if (liLen < 9)  {
        for(var i = 0; i<len; i++){
            var file_name = files[i].name; //文件名
            //构造html来更新UI
            html += '<li class="uploading" id="file-' + files[i].id +'"><span class="cmt-speed"></span><span class="iconcom-del"></span></li>';
        }

        $fileBox.append(html);
        uploader.start();
        cmtUPinfo();
    }

    $(".moxie-shim").css('top',($fileBox.height() + 103));
});

//已上传数量更新
function cmtUPinfo() {
    var liLen = $fileBox.find('li').length;
    liLen == 0 ? $cmtUpInf.hide() : $cmtUpInf.show().html('<span class="iconcom-warning"></span>已上传 <dfn>' + liLen + '</dfn> 张，还能上传 <dfn>' + (9-liLen) + '</dfn> 张');
}

//绑定文件上传进度事件
uploader.bind('UploadProgress',function(uploader,file){
    $('#file-'+file.id+' .cmt-speed').html('已上传' + file.percent + '%');
});

//上传完毕触发
uploader.bind('FileUploaded',function(uploader,file,responseObject){
    //替换预览图

    $('#file-'+file.id).append('<span class="compicbox"><img src="http://placehold.it/90x60"></span>').removeClass('uploading');

    if (responseObject.status == 200) {
        var res = JSON.parse(responseObject.response);
        // console.log(res);
        $('#file-' + file.id ).find(".compicbox").html('<img src="http://pic.lvmama.com'+res.smallImg+'"alt="" width="90" height="60">');
        //创建图片form表单input
        var picInput ="<input type=\"hidden\" name=\"pictures\" value="+ res.imgPath +">";
        $('#file-'+file.id).append(picInput).removeClass('uploading');
    }

});


/*
 * 绑定删除事件委托
 */
$fileBox.on('click','.iconcom-del',function (){
    var me = $(this),
        thisParent = $(this).parent(); //父li节点
    thisid = thisParent.attr('id'); //取得要删除的id号

    //删除节点
    thisParent.stop(true,true).animate({'width':0}, 200,function (){
        thisParent.remove();
        cmtUPinfo();
    });

});