import { PageContainer } from '@ant-design/pro-components';
import message from 'antd/lib/message';
import React, {useEffect, useState} from 'react';
import {listInterfaceInfoByPageUsingGet} from "@/services/api-backed/interfaceInfoController";
import { List } from 'antd';

const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [list, setList] = useState<API.InterfaceInfo[]>([]);
  const [total, setTotal] = useState<number>(0);

  const loadData = async (current = 1, pageSize = 5) => {
    setLoading(true);
    try {
      const res = await listInterfaceInfoByPageUsingGet({
        current,
        pageSize
      });
      setList(res?.data?.records ?? []);
      setTotal(res?.data?.total ?? 0);
    }catch (error: any) {
      message.error("请求失败");
    }
    setLoading(false);
  }

  // 页面加载完调用函数
  useEffect(() => {
    loadData();
  },[]);
  return (
    <PageContainer title="在线接口开放平台">
      <List
        className="api-list"
        loading={loading}
        itemLayout="horizontal"
        dataSource={list}

        renderItem={(item) => (


              <List.Item actions={[<a key={item.id} href={'/interface_info/'+item.id}>查看</a>]}>
              <List.Item.Meta
                title={<a href={'/interface_info/'+item.id}>{item.name}</a>}
                description={item?.description}/>
                </List.Item>

        )}
        pagination={{
          showTotal(all: number) {
            return '总数：' + all;
          },
          pageSize: 5,
          total,
          onChange(page,pageSize) {
            loadData(page, pageSize);
          },
        }}
      />
    </PageContainer>
  );
};
export default Index;
