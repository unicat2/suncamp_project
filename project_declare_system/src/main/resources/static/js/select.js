//select.js

//引入新的插件
layui.config({
    // base: './mods/'//拓展模块的根目录
    base: './js/'//拓展模块的根目录
}).extend({
    pca: 'pca'
});

//使用自定义的插件pca
layui.use(['form', 'layedit', 'laydate', 'upload', "jquery", "pca"], function () {
    var $ = layui.$
        , form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , layarea = layui.layarea
        , laydate = layui.laydate
        , pca = layui.pca;
    //带初始值进行初始化
    pca.init('select[name=P1]', 'select[name=C1]', 'select[name=A1]', '浙江', '杭州', '滨江区');

    //日期
    laydate.render({
        elem: '#date'
    });
    laydate.render({
        elem: '#date1'
    });

    //自定义验证规则
    form.verify({
        title: function (value) {
            if (value.length < 5) {
                return '标题至少得5个字符啊';
            }
        }
        , pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
        , content: function (value) {
            layedit.sync(editIndex);
        }
    });

    //不带初始值
    // pca.init('select[name=P1]', 'select[name=C1]', 'select[name=A1]');

    //输入提示
    $("#address").bind('input propertychange', function () {
            var address = document.getElementById("address").value;
            if (address == "") {
                document.getElementById("addressTip").style.display = "none";
                return;
            }
            var html = '';
            var province = document.getElementById("province").value;
            if (province == "全部") {
                province = '';
            }
            var city = document.getElementById("city").value;
            if (city == "全部") {
                city = '';
            }
            var area = document.getElementById("area").value;
            if (area == "全部") {
                area = '';
            }

        }
    );

});

function submitFunction() {
    parent.$('#next', parent.document).trigger('click');
}