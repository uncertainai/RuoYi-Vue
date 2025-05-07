import request from '@/utils/request'

// 查询剧目显示规则列表
export function listDrama_rules(query) {
  return request({
    url: '/app/drama_rules/list',
    method: 'get',
    params: query
  })
}

// 查询剧目显示规则详细
export function getDrama_rules(id) {
  return request({
    url: '/app/drama_rules/' + id,
    method: 'get'
  })
}

// 新增剧目显示规则
export function addDrama_rules(data) {
  return request({
    url: '/app/drama_rules',
    method: 'post',
    data: data
  })
}

// 修改剧目显示规则
export function updateDrama_rules(data) {
  return request({
    url: '/app/drama_rules',
    method: 'put',
    data: data
  })
}

// 删除剧目显示规则
export function delDrama_rules(id) {
  return request({
    url: '/app/drama_rules/' + id,
    method: 'delete'
  })
}
