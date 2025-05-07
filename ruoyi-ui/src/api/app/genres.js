import request from '@/utils/request'

// 查询剧目类型列表
export function listGenres(query) {
  return request({
    url: '/app/genres/list',
    method: 'get',
    params: query
  })
}

// 查询剧目类型详细
export function getGenres(id) {
  return request({
    url: '/app/genres/' + id,
    method: 'get'
  })
}

// 新增剧目类型
export function addGenres(data) {
  return request({
    url: '/app/genres',
    method: 'post',
    data: data
  })
}

// 修改剧目类型
export function updateGenres(data) {
  return request({
    url: '/app/genres',
    method: 'put',
    data: data
  })
}

// 删除剧目类型
export function delGenres(id) {
  return request({
    url: '/app/genres/' + id,
    method: 'delete'
  })
}
