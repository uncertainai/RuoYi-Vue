import request from '@/utils/request'

// 查询用户登录记录列表
export function listLogin_records(query) {
  return request({
    url: '/app/login_records/list',
    method: 'get',
    params: query
  })
}

// 查询用户登录记录详细
export function getLogin_records(id) {
  return request({
    url: '/app/login_records/' + id,
    method: 'get'
  })
}

// 新增用户登录记录
export function addLogin_records(data) {
  return request({
    url: '/app/login_records',
    method: 'post',
    data: data
  })
}

// 修改用户登录记录
export function updateLogin_records(data) {
  return request({
    url: '/app/login_records',
    method: 'put',
    data: data
  })
}

// 删除用户登录记录
export function delLogin_records(id) {
  return request({
    url: '/app/login_records/' + id,
    method: 'delete'
  })
}
