

import request from '@/utils/request'

// 创建案例{
//     "completed": 1,
//     "id": 54
// }
export function taskCompleted(data) {
    return request({
        url: '/edu/caseTask',
        method: 'put',
        data
    })
}
//获取学生账号作业
export function getWork(params) {
    return request({
        url: '/edu/case/myList',
        method: 'get',
        params
    })
}

 
//获取学生账号作业文件  caseId
export function getFile(params) {
    return request({
        url: '/edu/flow/info',
        method: 'get',
        params
    })
}

