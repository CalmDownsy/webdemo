$(function () {
    selectLoad();
});

function selectLoad() {
    var html = '';
    $.ajax({
        url: '/common/dict/type',
        success: function (data) {
            // 加载下拉选内容
            // console.log(data);
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].type + '">' + data[i].description + '</option>';
            }
            // class 选择器
            $('.chosen-select').append(html);
            $('.chosen-select').chosen({
                maxHeight: 200
            });
            
            // 选中下拉选的value
            $('.chosen-select').on('change', function (e, params) {
                console.log(params.selected);
            })
        }
    });
}