$(function () {
    selectLoad();
    load();
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
            // e 不能少，否则拿不到选中的值
            $('.chosen-select').on('change', function (e, params) {
                console.log(params.selected);
                var opt = {
                    query: {
                        type: params.selected
                    }
                }
                $('#exampleTable').bootstrapTable('refresh', opt);
            });
        }
    });
}

function load() {
    $('#exampleTable').bootstrapTable({
        method: 'get',
        url: '/common/dict/list',
        iconSize: 'outline',
        // toolbar: '#exampleToolbar',
        striped: true,
        dataType: 'json',
        pagination: true,
        singleSelect: false,
        pageSize: 10,
        pageNumber: 1,
        showColums: false,
        sidePagination: 'server',
        queryParams: function (params) {
            return {
                limit: params.limit,
                offset: params.offset,
                // type: $('#searchName').val()
            }
        },
        // 设置显示的字段内容
        columns: [
            {
                checkbox: true
            },
            {
                field: 'id',
                title: '编号'
            },
            {
                field: 'name',
                title: '标签名'
            },
            {
                field: 'value',
                title: '数据值',
                width: '100px'
            },
            {
                field: 'type',
                title: '类型'
            },
            {
                field: 'description',
                title: '描述'
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                // value 不能少，否则拿不到row.id
                formatter: function (value, row) {
                    var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                        + row.id
                        + '\')"><i class="fa fa-edit"></i></a> ';
                    var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                        + row.id
                        + '\')"><i class="fa fa-remove"></i></a> ';
                    var f = '<a class="btn btn-success btn-sm ' + s_add_h + '" href="#" title="增加"  mce_href="#" onclick="addD(\''
                        + row.type + '\',\'' + row.description
                        + '\')"><i class="fa fa-plus"></i></a> ';
                    return e + d + f;
                }
            }]
    });
}

function reload() {
    var opt = {
        query: {
            type: $('.chosen-select').val()
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function add() {
    layer.open({
        type: 2,
        title: '新增',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '480px'],
        content: '/common/dict/add'
    });
}

function edit(id) {
    console.log('/common/dict/edit/' + id);
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '480px'],
        content: '/common/dict/edit/' + id
    });
}

function batchRemove() {
    // 使用 bootstrapTable
    var rows = $('#exampleTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        layer.msg('请选择需要删除的数据');
        return;
    }
    layer.confirm('确认要删除' + rows.length + '条数据吗？',
        {
            btn: ['确定', '取消']
        },
        function () {
            var ids = new Array();
            $.each(rows, function (i, row) {
                ids[i] = row['id'];
            });
            $.ajax({
                type: 'post',
                url: '/common/dict/batchRemove',
                data: {
                    'ids': ids
                },
                dataType: 'json',
                // 防止深度序列化
                traditional: true,
                success: function (result) {
                    if (result.code == 0) {
                        layer.msg(result.msg);
                        reload();
                    } else {
                        layer.msg(result.msg);
                    }
                }
            });
        });
}