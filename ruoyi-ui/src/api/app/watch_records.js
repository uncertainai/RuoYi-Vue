import request from '@/utils/request'

// 查询用户观看记录列表
export function listWatch_records(query) {
  return request({
    url: '/app/watch_records/list',
    method: 'get',
    params: query
  })
}

// 查询用户观看记录详细
export function getWatch_records(id) {
  return request({
    url: '/app/watch_records/' + id,
    method: 'get'
  })
}

// 新增用户观看记录
export function addWatch_records(data) {
  return request({
    url: '/app/watch_records',
    method: 'post',
    data: data
  })
}

// 修改用户观看记录
export function updateWatch_records(data) {
  return request({
    url: '/app/watch_records',
    method: 'put',
    data: data
  })
}

// 删除用户观看记录
export function delWatch_records(id) {
  return request({
    url: '/app/watch_records/' + id,
    method: 'delete'
  })
}
