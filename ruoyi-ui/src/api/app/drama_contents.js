import request from '@/utils/request'

// 查询剧目内容列表
export function listDrama_contents(query) {
  return request({
    url: '/app/drama_contents/list',
    method: 'get',
    params: query
  })
}

// 查询剧目内容详细
export function getDrama_contents(id) {
  return request({
    url: '/app/drama_contents/' + id,
    method: 'get'
  })
}

// 新增剧目内容
export function addDrama_contents(data) {
  return request({
    url: '/app/drama_contents',
    method: 'post',
    data: data
  })
}

// 修改剧目内容
export function updateDrama_contents(data) {
  return request({
    url: '/app/drama_contents',
    method: 'put',
    data: data
  })
}

// 删除剧目内容
export function delDrama_contents(id) {
  return request({
    url: '/app/drama_contents/' + id,
    method: 'delete'
  })
}
