import request from '@/utils/request'

// 查询社交媒体用户列表
export function listUsers(query) {
  return request({
    url: '/app/users/list',
    method: 'get',
    params: query
  })
}

// 查询社交媒体用户详细
export function getUsers(id) {
  return request({
    url: '/app/users/' + id,
    method: 'get'
  })
}

// 新增社交媒体用户
export function addUsers(data) {
  return request({
    url: '/app/users',
    method: 'post',
    data: data
  })
}

// 修改社交媒体用户
export function updateUsers(data) {
  return request({
    url: '/app/users',
    method: 'put',
    data: data
  })
}

// 删除社交媒体用户
export function delUsers(id) {
  return request({
    url: '/app/users/' + id,
    method: 'delete'
  })
}
