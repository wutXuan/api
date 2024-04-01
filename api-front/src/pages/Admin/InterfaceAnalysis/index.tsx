import React, {useEffect, useState} from "react";
import ReactECharts from 'echarts-for-react';
import {PageContainer} from "@ant-design/pro-components";
import {listTopInterfaceInfoUsingGet} from "@/services/api-backed/analysisController";


const InterfaceAnalysis: React.FC = () => {

  const [data,setData] = useState<API.InterfaceInfoVO[]>([]);


  useEffect(() => {
    try {
      listTopInterfaceInfoUsingGet().then(res =>{
        if(res.data){
          setData(res.data);
        }
      })
    }catch (e: any){

    }

  },[]);

  const chartData = data.map(item => {
    return{
      value: item.totalSum,
      name: item.name,
    }
  })

  const option= {
    title: {
      text: '调用次数最多TOP3',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: 'Access From',
        type: 'pie',
        radius: '50%',
        data: chartData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };


  return (
    <PageContainer>
      <ReactECharts option={option} />
    </PageContainer>
  );

};

export default InterfaceAnalysis;
