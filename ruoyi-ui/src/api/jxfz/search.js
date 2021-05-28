import request from '@/utils/request'

// 搜索流程图席位
//     "keyword": 
//     "category": 
export function searchList(params) {
    return request({
        url: '/edu/search/list',
        method: 'get',
        params
    })
}