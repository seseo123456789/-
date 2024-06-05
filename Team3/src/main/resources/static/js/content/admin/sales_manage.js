const money_td = document.querySelector('.money-td');
const money = parseInt(money_td.innerText);
money_td.innerText = money.toLocaleString() + ' 원';
const ctx = document.querySelector('.bar-chart-div');
const payYear = document.querySelector('select').value;
let mychart = null;
showGraphic(payYear);

function showGraphic(payYear){
    const deleteGraph = document.querySelector('.bar-chart-div');


    fetch('/admin/showGraphic', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
           payYear : payYear
        })
    })
    .then((response) => {
        // return response.text();
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((datas) => {//data -> controller에서 리턴되는 데이터!

        let arr = [];
        let result =[];
        let sumSales = 0;
        let sum = 0;

        
        datas.forEach((e, i)=>{
            arr.push(e);
        });
        if(arr != null){
            arr.forEach((element, idx)=>{
                result.push(element.data);

            });
            if(result[0] != null){
                result[0].forEach((monthly, t)=>{
                    sumSales =  monthly + sumSales;
                    
                });
            }
        }
        money_td.innerHTML='';
        sum = sumSales * 10000;
        money_td.innerText = sum.toLocaleString() + ' 원';
        

        mychart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            datasets: [{
                label: '(만)원',
                data: result[0],
                borderWidth: 1,
                borderColor: 'red'
            }, {
                label: '명',
                data: result[1],
                borderWidth: 1,
                borderColor: 'blue'
            }]
        }, 
        options: {
            scales: {
            y: {
                beginAtZero: true
            }
            }
        }
        });
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
 
}

function eventYear(){
    const selectEvent = document.querySelector('select').value;
    mychart.destroy();
    showGraphic(selectEvent);
}