

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