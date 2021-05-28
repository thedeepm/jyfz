import request from '@/utils/request'

// 创建案例 {
//   "caseName": "TestCase",
//   "description": "测试新增案例",
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

// 删除案例
export function removeCase(id) {
    return request({
        url: '/edu/case/' + id,
        method: 'delete',
    })
}
// 查询案例
export function getCase(id) {
    return request({
        url: '/edu/case/' + id,
        method: 'get',
    })
}
// 案例列表
export function getCaseList(params) {
    return request({
        url: '/edu/case/list',
        method: 'get',
        params
    })
}
