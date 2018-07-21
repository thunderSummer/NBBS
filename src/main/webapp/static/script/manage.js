function sendAjax(url, type, data, successFunction) {
    $.ajax({
        url: url,
        type: type,
        dataType: 'json',
        timeout: 1000,
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
        },
        data: data,
        cache: false,
        success: successFunction //成功执行方法
    });
}

$(function () {
    getUserInfo();
});

function getUserInfo() {
    $(".nav-active").removeClass("nav-active");
    $(".part-info").hide();
    $(".user-manage").show();
    $(".user").addClass("nav-active");
    loadUserInfo();
}

function getPostInfo() {
    $(".nav-active").removeClass("nav-active");
    $(".post").addClass("nav-active");
    $(".part-info").hide();
    $(".post-manage").show();
    load();

}

function getSectionInfo() {
    $(".nav-active").removeClass("nav-active");
    $(".section").addClass("nav-active");
    $(".part-info").hide();
    $(".section-manage").show();
    loadSectionInfo();

}

function getPartitionInfo() {
    $(".nav-active").removeClass("nav-active");
    $(".partition").addClass("nav-active");
    $(".part-info").hide();
    $(".partition-wrapper").show();
    loadPartitionInfo();

}

function loadUserInfo() {
    sendAjax('/admin/allUser', 'get', '', function (innerRes) {
        $('.user-manage').empty();
        $('.user-manage').append(`
            <tr class="user-header">
                    <th>
                        用户ID
                    </th>
                    <th>
                        用户名字
                    </th>
                    <th>
                        版块版主
                    </th>
                    <th>
                        是否为版块版主
                    </th>
                    <th>
                        分区版主
                    </th>
                    <th>
                        是否为分区版主
                    </th>
                </tr>`);
        $.each(innerRes.data, function (i, item) {
            let even = '';
            if (i % 2 === 0) {
                even = 'even'
            }
            $('.user-manage').append(` 
 <tr class="user-header ${even}">
 <td>
                        ${item.id}
                    </td>
                    <td>
                        ${item.nickname}
                    </td>
                    <td>
                        <select id='user-section-setting-${item.id}' class='user-section-setting' style="display: block;height: 22px;" onchange="sectionSelectListener(this)">
                           <option value="未选择">未选择</option>
                        </select>

                    </td>
                    <td>
                        <i class="section-user-status-${item.id}">未知</i>
                        <i id="user-section-delete-${item.id}" class=" fa fa-fw fa-times" style=" float: right; margin: 0 20px;" onclick="onDeleteSectionOwner(this)"></i>
                        <i id="user-section-add-${item.id}" class=" fa fa-fw fa-check" style="float: right" onclick="onAddSectionOwner (this)"></i>
                    </td>
                    <td>
                        <select id="user-partition-setting-${item.id}" class="user-partition-setting" onchange="partitionSelectListener(this)" style="display: block;height: 22px;">
                        <option value="未选择">未选择</option>
                        </select>
                    </td>
                    <td>
                        <i class="partition-user-status-${item.id}">未知</i>
                        <i id="user-partition-delete-${item.id}" class=" fa fa-fw fa-times" style=" float: right;margin: 0 20px" onclick="onDeletePartitionOwner(this)"></i>
                        <i id="user-partition-add-${item.id}" class=" fa fa-fw fa-check" onclick="onAddPartitionOwner(this)" style="float: right"></i>
                    </td>
</tr> 
                    `)
        });
        sendAjax('/allPartition', 'get', '', function (res) {
            $.each(res.data, function (i, item) {
                $('.user-partition-setting').append(`
                    <option value="${item.id}">${item.title}</option>`
                )
            });
        });
        sendAjax('/allSection', 'get', '', function (res) {
            $.each(res.data, function (i, item) {
                $('.user-section-setting').append(`
                    <option value="${item.id}">${item.title}</option>`
                )
            })
        });

    });


}

// function loadPostInfo() {
//     sendAjax('/allPost','get','',function (res) {
//         if (res.meta.success === true) {
//             $('.post-manage').empty();
//             $('.post-manage').append(
//                 `
//                     <tr class="post-header">
//                     <th>
//                         帖子ID
//                     </th>
//                     <th>
//                         帖子标题
//                     </th>
//                     <th>
//                         用户
//                     </th>
//                     <th>
//                         帖子类型
//                     </th>
//                     <th>
//                         置顶类型
//                     </th>
//                     <th>
//                         删除
//                     </th>
//                 </tr>
//             `);
//             $.each(res.data, function (i, item) {
//                     $('.post-manage').append(`
//                     <tr class="post-row">
//                     <td>
//                     ${item.id}
//                     </td>
//                     <td>
//                     ${item.title}
//                     </td>
//                     <td>
//                     ${item.user.nickname}
//                     </td>
//                     <td>
//                         <select class="select-post-type" id="select-post-type-${item.id}">
//                             <option value="0">封禁</option>
//                             <option value="1">正常</option>
//                             <option value="2">精华</option>
//                             </select>
//                         <i class="fa fa-fw fa-check" id="i-post-type-${item.id}" style="float: right" onclick="changeType(this)"></i>
//                     </td>
//                     <td>
//                         <select class="select-post-top" id="select-post-top-${item.id}">
//                             <option value="0">正常</option>
//                             <option value="1">版块置顶</option>
//                             <option value="2">全论坛置顶</option>
//                         </select>
//                         <i class="fa fa-fw fa-check" id="i-post-top-${item.id}" style="float: right" onclick="changeTopping(this)"></i>
//                     </td>
//                     <td>
//                         <i class="fa fa-fw fa-times" id="i-post-delete-${item.id}" style="float: right" onclick="deletePost(this)">
//                         </i>
//                     </td>
//                 </tr>
//                 `);
//                     $("#select-post-type-${item.id}").val(${item.type});
//                     $("#select-post-top-${item.id}").val(${item.topping});
//         })
//         }
//
//         });
// }
function load() {
    sendAjax('/admin/allPost','get','',function (res) {
        if (res.meta.success===true){
            $('.post-manage').empty();
            $('.post-manage').append(
                `
               <tr class="post-header">
                    <th>
                        帖子ID
                    </th>
                    <th>
                        帖子标题
                    </th>
                    <th>
                        用户
                    </th>
                    <th>
                        帖子类型
                    </th>
                    <th>
                        置顶类型
                    </th>
                    <th>
                        删除
                    </th>
                </tr>
                `
            )
            $.each(res.data,function (i,item) {
                $('.post-manage').append(`
                    <tr class="post-row">
                    <td>
                    ${item.id}
                    </td>
                    <td>
                    ${item.title}
                    </td>
                    <td>
                    ${item.user.nickname}
                    </td>
                    <td>
                        <select class="select-post-type" id="select-post-type-${item.id}">
                            <option value="0">封禁</option>
                            <option value="1">正常</option>
                            <option value="2">精华</option>
                            </select>
                        <i class="fa fa-fw fa-check" id="i-post-type-${item.id}" style="float: right" onclick="changeType(this)"></i>
                    </td>
                    <td>
                        <select class="select-post-top" id="select-post-top-${item.id}">
                            <option value="0">正常</option>
                            <option value="1">版块置顶</option>
                            <option value="2">全论坛置顶</option>
                        </select>
                        <i class="fa fa-fw fa-check" id="i-post-top-${item.id}" style="float: right" onclick="changeTopping(this)"></i>
                    </td>
                    <td>
                        <i class="fa fa-fw fa-times" id="i-post-delete-${item.id}" style="float: right" onclick="deletePost(this)">
                        </i>
                    </td>
                </tr>
                `);
                let type = item.type;
                $(`#select-post-type-${item.id}`).val(type);
                $(`#select-post-top-${item.id}`).val(item.topping);

            })
        }
    })
}
function changeType(obj){
    let id = $(obj).attr("id").split('-')[3];
    let type = $(`.select-post-type-${id}`).val();
    let data ={
        "postId":id,
        "type":type
    };
    sendAjax('/admin/post/type','put',JSON.stringify(data),function (res) {
        alert("帖子类型更改成功")
    })
}
function changeTopping(obj) {
    let id = $(obj).attr("id").split('-')[3];
    let type = $(`.select-post-top-${id}`).val();
    let data ={
        "postId":id,
        "topping":type
    };
    sendAjax('/admin/post/top','put',JSON.stringify(data),function (res) {
        alert("帖子类型更改成功")
    })
}
function deletePost(obj) {
    let id=$(obj).attr("id").split('-')[3];
    let data={
        "postId":id
    };
    sendAjax('/admin/post','delete',JSON.stringify(data),function () {
        alert('帖子删除成功');
        getPostInfo();
    })

}
function loadPartitionInfo() {
    sendAjax('/allPartition', 'get', '', function (res) {
        $('.partition-manage').empty();
        $('.partition-manage').append(`<tr class="tag-header">
                        <th>
                            分区ID
                        </th>
                        <th>
                            分区名字
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>`);
        $.each(res.data, function (i, item) {
            let even = '';
            if (i % 2 === 0) {
                even = 'even'
            }
            $('.partition-manage').append(`
        <tr class="tag-header ${even}">
                        <td>
                            ${item.id}
                        </td>
                        <td id="partition-title-${item.id}">
                            ${item.title}
                        </td>
                        <td>
                            <input id="input-partition-update-${item.id}" ><i class="fa fa-fw fa-check" id="input-partition-i-${item.id}" style="margin-right: 20px;float: right" onclick="updatePartition(this)">
                        </i>
                        </td>
                    </tr>
        `)
        });

    })
}

function loadSectionInfo() {
    sendAjax('/allSection', 'get', '', function (res) {
        if (res.meta.success === true) {
            $('.section-manage').empty();
            console.log(1);
            $('.section-manage').append(`             
             <tr class="section-header">
                    <th>
                        版块ID
                    </th>
                    <th>
                        所属分区
                    </th>
                    <th>
                        版块名字
                    </th>
                    <th>
                        更改分区
                    </th>
                    <th>
                        更改名字
                    </th>
                </tr>`);
            let even = '';
            $.each(res.data, function (i, item) {
                if (i % 2 === 0) {
                    even = 'even'
                } else {
                    even = ''
                }
                $('.section-manage').append(`<tr class="section-detail ${even}">
                    <td>
                        ${item.id}
                    </td>
                    <td class="belong-partition-title-${item.id}">
                        ${item.partition.title}
                    </td>
                    <td class="section-title-${item.id}">
                        ${item.title}
                    </td>
                    <td>
                        <select id="belong-partition-select-${item.id}" class="belong-partition-select">
                            
                        </select>
                        <i class="fa fa-fw fa-check" style="float: right" id="change-select-partition-${item.id}" onclick="changeSectionPartition(this)"></i>
                    </td>
                    <td>
                    <input id="input-section-${item.id}" style="display: inline-block;">
                    <i class="fa fa-fw fa-check" style="float: right" id="change-select-title-${item.id}" onclick="changeSectionTitle(this)"></i>
                    </td>
                </tr>`)
            });
            if (even === '') {

            } else {
                even = 'even';
            }
            $('.section-manage').append(`<tr class="section-detail ${even}">
                    <td>
                       无法操作
                    </td>
                    <td class="belong-partition-title-new">
                        无法操作
                    </td>
                    <td class="section-title-new">
                        <input id="input-section-new" style="height: 40px">
                    </td>
                    <td>
                        <select id="belong-partition-select-new" class="belong-partition-select">
                            
                        </select>
                        <i class="fa fa-fw fa-check" style="float: right" id="change-select-partition-new" onclick="onAddSection()"></i>
                    </td>
                    <td>
                    无法操作
                    </td>
     </tr> `);
            sendAjax('/allPartition', 'get', '', function (res) {
                $.each(res.data, function (i, item) {
                    $('.belong-partition-select').append(`
                    <option value="${item.id}">${item.title}</option>`
                    )
                });
            })
        }
    })
}

function partitionSelectListener(obj) {
    let result = $(obj).children('option:selected').val();
    let id = $(obj).attr("id").split('-')[3];
    if (result === "未选择") {
        $(`.section-user-status-${id}`).text("未知")
    } else {
        let data = {
            "id": result,
            "userId": id
        };
        sendAjax('/admin/isPartition', 'post', JSON.stringify(data), function (res) {
            let p = `.partition-user-status-${res.data.id}`;
            if (res.data.result === true) {
                $(p).text("是");
            } else {
                $(p).text("否")
            }
        })
    }

}

function sectionSelectListener(obj) {
    let result = $(obj).children('option:selected').val();
    let id = $(obj).attr('id').split('-')[3];
    if (result === "未选择") {
        $(`.section-user-status-${id}`).text("未知")
    } else {

        let data = {
            "id": result,
            "userId": id
        };
        sendAjax('/admin/isSection', 'post', JSON.stringify(data), function (res) {
            let p = `.section-user-status-${res.data.id}`;
            if (res.data.result === true) {
                $(p).text("是");
            } else {
                $(p).text("否")
            }
        })
    }

}

function onDeleteSectionOwner(obj) {
    let id = $(obj).attr('id').split('-')[3];
    let p = `.section-user-status-${id}`;
    if ($(p).text().trim() === "是") {
        let select = `#user-section-setting-${id}`;
        let data = {
            "id": $(select).val(),
            "userId": id
        };
        sendAjax('admin/sectionOwner', 'delete', JSON.stringify(data), function (res) {
            if (res.meta.success === true) {
                alert("更改成功");
                $(p).text("否");
            }
        })
    }

}

function onAddSectionOwner(obj) {
    let id = $(obj).attr('id').split('-')[3];
    let p = `.section-user-status-${id}`;
    if ($(p).text().trim() === "否") {
        let select = `#user-section-setting-${id}`;
        let data = {
            "id": $(select).val(),
            "userId": id
        };
        sendAjax('admin/sectionOwner', 'post', JSON.stringify(data), function (res) {
            if (res.meta.success === true) {
                alert("更改成功");
                $(p).text("是");
            }
        })
    }

}

function onDeletePartitionOwner(obj) {
    let id = $(obj).attr('id').split('-')[3];
    let p = `.partition-user-status-${id}`;
    if ($(p).text().trim() === "是") {
        let select = `#user-partition-setting-${id}`;
        let data = {
            "id": $(select).val(),
            "userId": id
        };
        sendAjax('admin/partitionOwner', 'delete', JSON.stringify(data), function (res) {
            if (res.meta.success === true) {
                alert("更改成功");
                $(p).text("否");
            }
        })
    }

}

function onAddPartitionOwner(obj) {
    let id = $(obj).attr('id').split('-')[3];
    let p = `.partition-user-status-${id}`;
    if ($(p).text().trim() === "否") {
        let select = `#user-partition-setting-${id}`;
        let data = {
            "id": $(select).val(),
            "userId": id
        };
        sendAjax('admin/partitionOwner', 'post', JSON.stringify(data), function (res) {
            if (res.meta.success === true) {
                alert("更改成功");
                $(p).text("是");
            }
        })
    }
}

function updatePartition(obj) {
    let id = $(obj).attr('id').split('-')[3];
    let input = $(`#input-partition-update-${id}`).val();
    let data = {
        "id": id,
        "title": input
    };
    sendAjax('admin/partition', 'put', JSON.stringify(data), function (res) {
        if (res.meta.success === true) {
            alert("更改成功");
            $(`#partition-title-${id}`).text(input);
        }
    })

}

function addPartition() {
    let input = $(`#input-partition`).val();
    let data = {
        "title": input
    };
    sendAjax('admin/partition', 'post', JSON.stringify(data), function (res) {
        if (res.meta.success === true) {
            alert("添加成功");
            loadPartitionInfo();

        }
    })
}

function changeSectionPartition(obj) {
    let id = $(obj).attr('id').split('-')[3];
    let partitionId = $(`.belong-partition-select-${id}`).children('option:selected').val();
    let data = {
        "id": id,
        "partition": {
            "id": partitionId
        }
    };
    sendAjax('/admin/section/partition', 'put', JSON.stringify(data), function (res) {
        if (res.meta.success === true) {
            $(`.belong-partition-title-${id}`).text($(`#belong-partition-select-3 option:selected`).text());
            alert("修改成功")
        }
    })
}

function onAddSection() {
    let title = $(`#input-section-new`).val();
    let data = {
        "title": title,
        "partition": {
            "id": $(`#belong-partition-select-new`).children('option:selected').val()
        },
        "announcement": `欢迎来到${title},祝你玩的愉快`
    };
    sendAjax(`/admin/section`, `post`, JSON.stringify(data), function (res) {
        if (res.meta.success === true) {
            alert("增加版块成功");
            loadSectionInfo();
        }
    })
}

function changeSectionTitle(obj) {
    let id = $(obj).attr('id').split('-')[3];
    let data = {
        "id": id,
        "title": $(`#input-section-${id}`).val()
    };
    sendAjax(`/admin/section`, 'put', JSON.stringify(data), function (res) {
        if (res.meta.success === true) {
            alert("修改名字成功");
            $(`.section-title-${id}`).text($(`#input-section-${id}`).val())
        }
    })
}