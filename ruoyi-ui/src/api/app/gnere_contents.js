import request from '@/utils/request'

// 查询剧目类型内容列表
export function listGnere_contents(query) {
  return request({
    url: '/app/gnere_contents/list',
    method: 'get',
    params: query
  })
}

// 查询剧目类型内容详细
export function getGnere_contents(id) {
  return request({
    url: '/app/gnere_contents/' + id,
    method: 'get'
  })
}

// 新增剧目类型内容
export function addGnere_contents(data) {
  return request({
    url: '/app/gnere_contents',
    method: 'post',
    data: data
  })
}

// 修改剧目类型内容
export function updateGnere_contents(data) {
  return request({
    url: '/app/gnere_contents',
    method: 'put',
    data: data
  })
}

// 删除剧目类型内容
export function delGnere_contents(id) {
  return request({
    url: '/app/gnere_contents/' + id,
    method: 'delete'
  })
}
