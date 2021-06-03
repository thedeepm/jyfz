import request from '@/utils/request'

// 搜索
export function search(type, params) {
    return request({
        url: `geog/${type}/list`,
        method: 'get',
        params
    })
}