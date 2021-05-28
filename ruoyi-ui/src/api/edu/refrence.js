import request from '@/utils/request'

// 查询流程图列表
export function listRefrence(query) {
  return request({
    url: '/edu/refrence/list',
    method: 'get',
    params: query
  })
}

// 查询流程图详细
export function getRefrence(id) {
  return request({
    url: '/edu/refrence/' + id,
    method: 'get'
  })
}

// 新增流程图
export function addRefrence(data) {
  return request({
    url: '/edu/refrence',
    method: 'post',
    data: data
  })
}

// 修改流程图
export function updateRefrence(data) {
  return request({
    url: '/edu/refrence',
    method: 'put',
    data: data
  })
}

// 删除流程图
export function delRefrence(id) {
  return request({
    url: '/edu/refrence/' + id,
    method: 'delete'
  })
}

// 导出流程图
export function exportRefrence(query) {
  return request({
    url: '/edu/refrence/export',
    method: 'get',
    params: query
  })
}