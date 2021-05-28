import request from '@/utils/request'

// 创建模板分区
export function createTemplate(data, id) {
    if (!id) {
        return request({
            url: '/edu/template',
            method: 'post',
            data
        })
    } else {
        return request({
            url: '/edu/template',
            method: 'put',
            data
        })
    }

}
// 获取模板分区
export function getTemplate(id) {
    return request({
        url: '/edu/template/' + id,
        method: 'get',
    })
}
// 删除模板分区
export function removeTemplate(id) {
    return request({
        url: '/edu/template/' + id,
        method: 'delete',
    })
}
// 创建模板分组
export function createGroup(data) {
    return request({
        url: '/edu/group',
        method: 'post',
        data
    })
}
// 创建流程
export function createFlow(data) {
    return request({
        url: '/edu/flow',
        method: 'post',
        data
    })
}

// 获取分区列表
export function getPartitionList(params) {
    return request({
        url: '/edu/partition/list',
        method: 'get',
        params
    })
}

// 获取分组列表
export function getGroupList(params) {
    return request({
        url: '/edu/group/list',
        method: 'get',
        params
    })
}
// 获取席位列表
export function getSeatList(params) {
    return request({
        url: '/edu/seat/list',
        method: 'get',
        params
    })
}
// 新建步骤
export function createTask(data) {
    return request({
        url: '/edu/task',
        method: 'post',
        data
    })
}


// 查询流程信息
export function getFlow(id) {
    return request({
        url: '/edu/flow/' + id,
        method: 'get',
    })
}
// 查询步骤信息
export function getTask(params) {
    return request({
        url: '/edu/task/list',
        method: 'get',
        params
    })
}
//修改流程
export function updateFlow(data) {
    return request({
        url: '/edu/flow',
        method: 'put',
        data
    })
}

//模板列表
export function getTemplateList(params) {
    return request({
        url: '/edu/template/list',
        method: 'get',
        params
    })
}
