import request from '@/utils/request'

// 查询国家语言配置列表
export function listLanguages(query) {
  return request({
    url: '/app/languages/list',
    method: 'get',
    params: query
  })
}

// 查询国家语言配置详细
export function getLanguages(id) {
  return request({
    url: '/app/languages/' + id,
    method: 'get'
  })
}

// 新增国家语言配置
export function addLanguages(data) {
  return request({
    url: '/app/languages',
    method: 'post',
    data: data
  })
}

// 修改国家语言配置
export function updateLanguages(data) {
  return request({
    url: '/app/languages',
    method: 'put',
    data: data
  })
}

// 删除国家语言配置
export function delLanguages(id) {
  return request({
    url: '/app/languages/' + id,
    method: 'delete'
  })
}
