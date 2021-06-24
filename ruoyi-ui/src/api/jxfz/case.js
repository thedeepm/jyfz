import request from '@/utils/request'

// 创建演示流程 {
//   "caseName": "TestCase",
//   "description": "测试新增演示流程",
//   "templateId": 62,
//   "type": "string"
// }
export function createCase(data) {
    return request({
        url: '/edu/case',
        method: 'post',
        data
    })
}
export function updateCase(data) {
    return request({
        url: '/edu/case',
        method: 'put',
        data
    })
}

// 删除演示流程
export function removeCase(id) {
    return request({
        url: '/edu/case/' + id,
        method: 'delete',
    })
}
// 查询演示流程
export function getCase(id) {
    return request({
        url: '/edu/case/' + id,
        method: 'get',
    })
}
// 演示流程列表
export function getCaseList(params) {
    return request({
        url: '/edu/case/list',
        method: 'get',
        params
    })
}
