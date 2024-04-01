import { PageContainer } from '@ant-design/pro-components';
import message from 'antd/lib/message';
import React, {useEffect, useState} from 'react';
import {
  getInterfaceInfoByIdUsingGet, invokeInterfaceInfoUsingPost,
} from "@/services/api-backed/interfaceInfoController";
import {Button, Card, Form} from 'antd';
import {Descriptions} from 'antd'

import { useRouteMatch, useParams } from "react-router";
import TextArea from "antd/es/input/TextArea";
const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceInfo>();
  const [invokeRes, setInvokeRes] = useState<any>();
  const [invokeLoading, setInvokeLoading] = useState(false);

  // const match = useRouteMatch('/interface_info/:id');
  // alert(JSON.stringify(match));
  const params = useParams();
  const loadData = async () => {
    if(!params.id){
      message.error('参数不存在');
      return;
    }
    setLoading(true);
    try{
      const res = await getInterfaceInfoByIdUsingGet({
        id: Number(params.id),
      });
      setData(res.data);
    }catch (error: any){
      message.error('请求失败');
    }
    setLoading(false);
  };

  // 页面加载完调用函数
  useEffect(() => {
    loadData();
  },[]);

  const onFinish = async (values: any) => {
    console.log('Success:', values);

    if(!params.id){
      message.error("接口不存在");
      return;
    }
    setInvokeLoading(true);
    try{
      const res = await invokeInterfaceInfoUsingPost({
        id: params.id,
        ...values
      })
      setInvokeRes(res.data);

    }catch (error: any){
      message.error("请求失败");
    }
    setInvokeLoading(false);
  };

  return (
    <PageContainer title="在线接口开放平台">
      {
        <Card>
          {data ? (
              <Descriptions title={data.name} column={1}>
                <Descriptions.Item label="接口状态">{data.status === 0 ? '关闭': '开启'}</Descriptions.Item>
                <Descriptions.Item label="描述">{data.description}</Descriptions.Item>
                <Descriptions.Item label="请求地址">{data.url}</Descriptions.Item>
                <Descriptions.Item label="请求方法">{data.method}</Descriptions.Item>
                <Descriptions.Item label="请求头">{data.requestHeader}</Descriptions.Item>
                <Descriptions.Item label="请求参数">{data.requestParams}</Descriptions.Item>
                <Descriptions.Item label="响应头">{data.responseHeader}</Descriptions.Item>
                <Descriptions.Item label="创建时间">{data.createTime}</Descriptions.Item>
                <Descriptions.Item label="更新时间">{data.updateTime}</Descriptions.Item>
              </Descriptions>
            ) : (<>接口不存在</>)
          }
        </Card>
      }
      {
        <Card>
          <Form name="invoke" layout={"vertical"} onFinish={onFinish}>
            <Form.Item label="请求参数" name="requestParams">
              <TextArea/>
            </Form.Item>

            <Form.Item wrapperCol={{ span: 16 }}>
              <Button type="primary" htmlType="submit">
                调用
              </Button>
            </Form.Item>
          </Form>
        </Card>
      }
      {
        <Card title={"返回结果"} loading={invokeLoading}>
          {invokeRes}
        </Card>
      }


    </PageContainer>
  );
};
export default Index;
