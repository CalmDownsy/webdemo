var formId = '#addDictForm';
$(function() {
    validateRule();
});
// 默认submit
// $.validator.setDefaults({
//     submitHandler: function() {
//         save();
//     }
// });
function save() {
    $.ajax({
        type: 'post',
        url: '/common/dict/save',
        data: $(formId).serialize(),
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                parent.layer.msg(result.msg);
                parent.reload();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                parent.layer.alert(result.msg);
            }
        },
        error: function () {
            parent.layer.alert('操作超时');
        }
    });
}

function validateRule() {
    var icon = '<i class="fa fa-times-circle"></i>';
    $(formId).validate({
        rules: {
            name: 'required'
        },
        messages: {
            name: icon + '请输入标签名'
        },
        submitHandler: function() {
            save();
        }
    });
}