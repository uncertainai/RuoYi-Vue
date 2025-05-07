import request from '@/utils/request'

// 查询剧目列表
export function listDrama(query) {
  return request({
    url: '/app/drama/list',
    method: 'get',
    params: query
  })
}

// 查询剧目详细
export function getDrama(id) {
  return request({
    url: '/app/drama/' + id,
    method: 'get'
  })
}

// 新增剧目
export function addDrama(data) {
  return request({
    url: '/app/drama',
    method: 'post',
    data: data
  })
}

// 修改剧目
export function updateDrama(data) {
  return request({
    url: '/app/drama',
    method: 'put',
    data: data
  })
}

// 删除剧目
export function delDrama(id) {
  return request({
    url: '/app/drama/' + id,
    method: 'delete'
  })
}
