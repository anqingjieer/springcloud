import vue from 'vue'
import {getDynamicUrl} from '@/utils'

const baseUrl = getDynamicUrl('')

const get = (path, query) => {
  return vue.prototype.$request(`${baseUrl}${path}`, query)
}

const post = (path, body) => {
  const reqBody = {}
  for (let key in body) {
    reqBody[key] = typeof body[key] === 'string' ? body[key].trim() : body[key]
  }
  return vue.prototype.$request(`${baseUrl}${path}`, reqBody, 'post', true)
}
export default {}

export const base = {

}
}
