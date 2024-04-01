// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** listTopInterfaceInfo GET /api/analysis/top/interface/invoke */
export async function listTopInterfaceInfoUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListInterfaceInfoVO>('/api/analysis/top/interface/invoke', {
    method: 'GET',
    ...(options || {}),
  });
}
