import {
    Chart as ChartJS,
    LineElement,
    PointElement,
    LineController,
    LinearScale,
    CategoryScale,
    Tooltip,
    Legend,
} from "chart.js";
import { Line } from 'react-chartjs-2';
import ChartDataLabels from 'chartjs-plugin-datalabels';



ChartJS.register(
    ChartDataLabels,
    LineElement,
    PointElement,
    LineController,
    LinearScale,
    CategoryScale,
    Tooltip,
    Legend,
)
const Graph = (weatherWeakly:any) => {
    console.log(weatherWeakly.data)
    // const labels = weatherWeakly?.map(data => data?.dayOfWeek);
    // const temperatureData = weatherWeakly?.map(data => parseFloat(data?.temperature));

    const data = {
        labels: [weatherWeakly.data[0].dayOfWeek, weatherWeakly.data[1].dayOfWeek, weatherWeakly.data[2].dayOfWeek, weatherWeakly.data[3].dayOfWeek, weatherWeakly.data[4].dayOfWeek, weatherWeakly.data[5].dayOfWeek, weatherWeakly.data[6].dayOfWeek],
        datasets: [
            {
                data: [weatherWeakly.data[0].temperature, weatherWeakly.data[1].temperature, weatherWeakly.data[2].temperature, weatherWeakly.data[3].temperature, weatherWeakly.data[4].temperature, weatherWeakly.data[5].temperature, weatherWeakly.data[6].temperature],
                fill: false,
                backgroundColor: '#fff',
               borderColor: 'rgba(255,255,255,0.63)',
                pointBorderWidth: 4,
                datalabels: {
                    color: '#fff',
                }

            }
        ]
    };

    const maxDataValue = Math.max(...data.datasets[0].data);

   const options= {
       plugins: {
           legend: {
               display: false
           },
           datalabels: {
               anchor: 'end',
               align: 'top',
               formatter: Math.round,
               font: {
                   weight: 'bold'
               }
           }
       },
       scales: {
           y: {
               ticks: {
                   display: false
               },
               grid: {
                   display: false,
                   drawBorder: false
               },
               max: maxDataValue + 5,
           }
       }
   };





    return (
        <div className={'w-full h-[80%] flex items-center justify-center mt-14 md:my-14'}>
            <Line
                data={data}
                // @ts-ignore
                options={options}
            />
        </div>
    );
};

export default Graph;
