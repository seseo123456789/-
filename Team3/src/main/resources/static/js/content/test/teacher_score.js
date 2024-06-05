
// ##########################(단일시험) 입력된 성적 수정 버튼 클릭시 성적조회 하여 input 뿌려주기 ##########################

const regex = /[^0-9]/g;

const updateScorBtn= document.querySelector('#updateScorBtn');

const regBtn = document.querySelector('#regBtn'); 
const scoreIn = document.querySelector('#score');

function goUpdateScores(testNum){


        // ------------------- 첫번째 방식 ---------------//
        fetch('/test/selectMainList', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
            // 데이터명 : 데이터값
            'testNum': testNum
            })
        })
        .then((response) => {
            if(!response.ok){
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return ;
            }

            //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data);
                    if(updateScorBtn.value=='수정'){
                                
                        const upScoreTbody = document.querySelector('.upScoreTbody');
                        upScoreTbody.innerHTML=''; 
                            let str='';                           
                            data.forEach(function(scordTd){
                                    str+=`
                                            <tr>
                                                <td>
                                                    ${scordTd.memberOneVO.memberName} [<Span>${scordTd.memberOneVO.memberId}</Span>]
                                                </td>
                                                <td>
                                                    <input type="number" value=${scordTd.score} class="scoreInput"  min ="0" max="${scordTd.testOneVo.testMaxScore}" name="score" style="width:100px;" onchange="maxScore(${scordTd.testOneVo.testMaxScore},this)">
                                                    <input type="hidden" value=${scordTd.scoreNum} name="scoreNum">
                                                </td>
                                                <td>
                                                    ${scordTd.testOneVo.testMaxScore}
                                                </td>
                                                <td>
                                                    ${scordTd.ranking}
                                                </td>

                                            </tr>
                                        `
                            });                            
                            upScoreTbody.insertAdjacentHTML('afterbegin',str);
                            document.querySelector('#updateScorBtn').value='저장';                             
                    }

                    else if(updateScorBtn.value=='저장'){
                        const scorePut = document.querySelector('.scoreInput');
                        goUpdate(scorePut); }
    
        })

        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}



// 점수 업데이트 실행! (빈값일때 경고문!)
function goUpdate(scorePut){

    if(scorePut.value == ''){
        alert('점수를 입력해주세요!');
        scorePut.value='';     
        return;
    }


    document.querySelector("#myform").submit();
}


//  경고문!! 총점 제한
function maxScore(subMaxScore, subInput){

    if (subInput.value > subMaxScore) {
        alert(`총점이 ${subMaxScore}입니다.\n점수를 다시 입력해주세요!`)
            subInput.value='';       
        return;
    }

    if(subInput.value.replace(regex,'') == ''){
        alert('점수입력창을 확인해주세요!');
            subInput.value='';
        return;
    }
    

}


// 처음 점수 저장 실행! (빈값일때 경고문!)
function regScore(){
    
    console.log(scoreIn.value);
    if(scoreIn.value == ''){
                alert('점수를 확인해주세요!');
                scoreIn.value=''; 
                return;
            }      
        document.querySelector('#insertStuScore').submit();                               
}


// 목록가기
function goFirstList(classNum){
    location.href="/test/scoreTeacher?classNum="+ classNum;
}
