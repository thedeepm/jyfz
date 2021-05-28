import request from '@/utils/request'

// 查询人员信息列表
export function listInfo(query) {
  return request({
    url: '/edu/info/list',
    method: 'get',
    params: query
  })
}

// 查询人员信息详细
export function getInfo(id) {
  return request({
    url: '/edu/info/' + id,
    method: 'get'
  })
}

// 新增人员信息
export function addInfo(data) {
  return request({
    url: '/edu/info',
    method: 'post',
    data: data
  })
}

// 修改人员信息
export function updateInfo(data) {
  return request({
    url: '/edu/info',
    method: 'put',
    data: data
  })
}

// 删除人员信息
export function delInfo(id) {
  return request({
    url: '/edu/info/' + id,
    method: 'delete'
  })
}

// 导出人员信息
export function exportInfo(query) {
  return request({
    url: '/edu/info/export',
    method: 'get',
    params: query
  })
}