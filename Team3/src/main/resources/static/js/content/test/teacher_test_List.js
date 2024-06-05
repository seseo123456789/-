


const test_detail_modal = new bootstrap.Modal('#testJoin-form');
const chTest  = document.querySelector('.chTest');
const upBtn = document.querySelector('.upBtn');
const downBtn = document.querySelector('.downBtn');

// #######################################(모달)  시험설정 버튼 클릭시 모달 창 오픈!!!!!#########################################


const classNum = document.querySelector('#classNum').value;

function happyBtn(classNum){
    

        // 시험추가 버튼 생성하기
        const testAddBtn = document.querySelector('.testAddBtn');        
        testAddBtn.innerHTML=''; 

                let str= `<div class="row" style="margin-top: 5px;">
                                <div class="col-2">
                                    <input type="button"  class="btn btn-secondary btn-sm" value="시험등록" id="btn" onclick="insertScore()"
                                    style="--bs-btn-hover-border-color:rgb(179, 179, 180);  --bs-btn-border-color:rgb(179, 179, 180); --bs-btn-bg: rgb(179, 179, 180);--bs-btn-hover-bg:rgb(179, 179, 180);">
                                </div>            
                            </div>`         
                            
                            testAddBtn.insertAdjacentHTML('afterbegin',str);



        // 과목 없음 / 과목 있음 선택시                            
        const classGoSub =document.querySelector('.classGoSub');
        const radios = document.querySelectorAll('input[type="radio"]');

        radios.forEach(function(rad,i){
                rad.addEventListener('click', function(e){

                        const current = e.currentTarget;
                        const hiddenPerfect =document.querySelector('#hiddenPerfect');

                        // 과목있음 선택시
                        if(current.value == 2){
                                    // 셀렉트 선택 해제
                                    chTest.value='';  
                                                                
                                    // 만점입력하는 칸 사라지게 함
                                    hiddenPerfect.innerHTML=``;
                                    let sti= ``;
                                    hiddenPerfect.insertAdjacentHTML('afterbegin',sti)

                                    // 시험명, 날짜만 저장하는 시험추가 버튼 생성
                                    testAddBtn.innerHTML=``;        
                                    let sth= `<div class="row" style="margin-top: 5px;">
                                                    <div class="col-2">
                                                        <input type="button"  class="btn btn-secondary btn-sm" value="시험등록" id="btn insertTestNameBtn " onclick="insertSubSc()"
                                                        style="--bs-btn-hover-border-color:rgb(179, 179, 180);  --bs-btn-border-color:rgb(179, 179, 180); --bs-btn-bg: rgb(179, 179, 180);--bs-btn-hover-bg:rgb(179, 179, 180);">
                                                    </div>
                                                    
                                                </div>`                                            
                                                testAddBtn.insertAdjacentHTML('afterbegin',sth);
                    
                                    // 과목 입력 생성 
                                    classGoSub.innerHTML=``;
                                    let stj=` <div class="row mt-3">
                                                    <div class="col" style="font-size:15px; color: red; font-weight: 15px;">
                                                            * 좌측 시험종류를 선택한 후, 과목을 입력해주세요.
                                                    </div>
                                                </div>

                                            <div class="row">
                                                <div class="col addSub">
                                            
                                                        <div class="row">
                                                            <div class="col-5">

                                                                        <div class="row">
                                                                            <div class="col">
                                                                
                                                                                <label for="selSub"> <strong>과목종류</strong></label>
                                                                                    <select class="form-select" size="3" aria-label="Size 3 select example"  id="selSub"  style="font-size: 15px;" >
                                                                                        <option></option>
                                                                                        <option></option>
                                                                                    </select>

                                                                            </div>
                                                                        </div>
                                                                        <div class="row mt-1">  
                                                                            <div class="col" id="totaltal">                                                                     
                                                                                <div class="row ">  
                                                                                    <div class="col">
                                                                                        <table>
                                                                                                <tr style="border:none;">
                                                                                                        <td style="border:none;"><strong>총만점</strong></td>
                                                                                                </tr>
                                                                                                <tr style="border:none;">
                                                                                                        <td id="subTotalMax" style="border:none;">  </td>
                                                                                                </tr>
                                                                                        </table>
                                                                                    </div> 
                                                                                </div>
                                                                            
                                                                            
                                                                            </div>
                                                                        </div>  
                                                                    
                                                                    
                                                                        
                                                            </div>
                                                            <div class="col-6">
                                                                        <div class="row">
                                                                            <div class="col" id="subMainTitleDiv">
                                                                                <input class="form-control" type="hidden" name="testNum" id="testNumForSub">
                                                                            </div>
                                                                        </div>
                                                                    
                                                                        <div class="row">
                                                                            <div class="col">
                                                                                <label for="subName"><strong>과목명</strong></label>
                                                                                <input class="form-control" type="text" name="subName" id="subName" >
                                                                            </div>
                                                                        </div>    
                                                                        <div class="row">
                                                                            <div class="col">
                                                                                <label for="subMaxScore"><strong>과목만점</strong></label>
                                                                                <input class="form-control" type="text" name="subMaxScore" id="subMaxScore">
                                                                            </div>       
                                                                        </div>
                                                                        <div class="row mt-1">
                                                                            <div class="col" id="addSubBtn">
                                                                    
                                                                                <input type="button"  class="btn btn-secondary btn-sm" id="subNameAdd" value="과목추가" )"
                                                                                style="--bs-btn-hover-border-color:rgb(179, 179, 180);  --bs-btn-border-color:rgb(179, 179, 180); --bs-btn-bg: rgb(179, 179, 180);--bs-btn-hover-bg:rgb(179, 179, 180);">
                                                                            </div>       
                                                                        </div>                   
                                                            </div>
                                                        </div>
                                                                            
                                                </div>   
                                            </div>`   

                                classGoSub.insertAdjacentHTML('afterbegin',stj);

                                
                        
                                        
                        }

                        else if(current.value == 1){
                                    hiddenPerfect.innerHTML=``;
                                    let stt = `<div class="row">
                                                    <div class="col" style="font-size: 18px;">
                                                        <strong>시험만점</strong>
                                                    </div>
                                                <div class="row">
                                                    <div class="col">
                                                        <input class="testInfoInput form-control"  type="text"  name="testMaxScore" id="testMaxScore" style="width: 75%;">
                                                    </div>
                                                </div>`
                                    
                                    hiddenPerfect.insertAdjacentHTML('afterbegin',stt)
                                    

                                    testAddBtn.innerHTML=``;                         
                                    let str= `<div class="row" style="margin-top: 5px;">
                                                    <div class="col-2">
                                                        <input type="button"  class="btn btn-secondary btn-sm" value="시험당" id="btn insertTestNameBtn " onclick="insertScore()"
                                                        style="--bs-btn-hover-border-color:rgb(179, 179, 180);  --bs-btn-border-color:rgb(179, 179, 180); --bs-btn-bg: rgb(179, 179, 180);--bs-btn-hover-bg:rgb(179, 179, 180);">
                                                    </div>
                                            </div>`
                                
                                            testAddBtn.insertAdjacentHTML('afterbegin',str);
                                    

                                            classGoSub.innerHTML=``;
                                            let stj='';
                                            classGoSub.insertAdjacentHTML('afterbegin',stj);                    
                                    
                        }

                });
        })


// ######################################(모달)  테스트 정보 조회  해서 모달로 가져오기  (1) ########################################
        

        // ---------------------------------------- --- 첫번째 방식 -----------------------------------//
        fetch('/test/selectTest', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                // 데이터명 : 데이터값
                'classNum' : classNum

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
    
                const sel = document.querySelector('#sel');           
                sel.innerHTML=``; 
                let std=``;
            
                data.forEach(function(testOne, idx){
                    std+=`<option  value=${testOne.testNum} id='optionNum'>${testOne.testName}</option>`;     
                    });
            
                    sel.insertAdjacentHTML('afterbegin',std); 
                    test_detail_modal.show();
                
                    // 오름차순 클릭
                    upBtn.addEventListener("click", function () {
                        let upTestName = data.sort((a,b) => {
                            if(a.testName > b.testName) return 1;
                            if(a.testName < b.testName) return -1;
                            return 0;
                        });                        
                        console.log(upTestName);
                                const sel = document.querySelector('#sel');           
                                sel.innerHTML=``; 
                                let std=``;
                                upTestName.forEach(function(testOne, idx){
                                    std+=`<option  value=${testOne.testNum} id='optionNum'>${testOne.testName}</option>`;     
                                    });
                            
                                    sel.insertAdjacentHTML('afterbegin',std); 
                                    test_detail_modal.show();

                    });

                    // 내림차순 클릭
                    downBtn.addEventListener("click", function () {
                        let downTestName = data.sort(function (a, b) {
                            return b.testName.localeCompare(a.testName);
                        });
                        console.log(downTestName);
                            const sel = document.querySelector('#sel');           
                            sel.innerHTML=``; 
                            let std=``;
                            downTestName.forEach(function(testOne, idx){
                                std+=`<option  value=${testOne.testNum} id='optionNum'>${testOne.testName}</option>`;     
                                });
                        
                                sel.insertAdjacentHTML('afterbegin',std); 
                                test_detail_modal.show();
                    });
        
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
        
    

        
}




// ####################################(모달) (과목없음)모달내의 시험등록 버튼 클릭시 insert되고 목록조회   ########################################


const regex = /[^0-9]/g;


function insertScore(){
    
                const testName =document.querySelector('#testName').value;
                const classNum = document.querySelector('#classNum').value;
                const testMaxScore = document.querySelector('#testMaxScore').value;
                const testDate= document.querySelector('#testDate').value;




                if(testName == '' 
                || testMaxScore == ''
                || testDate == ''    
                ){
                    alert('입력창을 재확인해주세요!');                    
                    return;
                }
                else if(testMaxScore.replace(regex,'') == ''
                    ){
                    alert('입력창을 다시 확인해주세요!');
                    return;
                    }


                
            fetch('/test/insertTest', { //요청경로
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                    },
                    //컨트롤러로 전달할 데이터
                    body: new URLSearchParams({
                    // 데이터명 : 데이터값
                    
                    'testName': testName,
                        'testMaxScore': testMaxScore,
                        'testDate': testDate,
                        'classNum' : classNum

                    })
                })
                .then((response) => {
                    if(!response.ok){
                        alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                        return ;
                    }
                
                    return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                    //return response.json(); //나머지 경우에 사용
                })
                //fetch 통신 후 실행 영역
                .then((data) => {//data -> controller에서 리턴되는 데이터!                   
                                        

                    InfoTestInput();
                    happyBtn(classNum);                
                })
                //fetch 통신 실패 시 실행 영역
                .catch(err=>{
                    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                    console.log(err);
                });
}


// #######################################(모달) (과목있음)과목있을때 메인테스트 저장 ################################


function insertSubSc(){

                const testName =document.querySelector('#testName').value;
                const classNum = document.querySelector('#classNum').value;
                const testDate= document.querySelector('#testDate').value;

                if(testName == '' 
                || testDate == ''    
                ){
                    alert('입력창을 재확인해주세요!');
                    testName='';
                    testDate='';                  
                    return;
                }
            

                    // ------------------- 첫번째 방식 ---------------//
                fetch('/test/insertSubSc', { //요청경로
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                    },
                    //컨트롤러로 전달할 데이터
                    body: new URLSearchParams({
                    // 데이터명 : 데이터값

                    'testName': testName,
                    'testDate': testDate,
                    'classNum' : classNum
                    })
                })
                .then((response) => {
                    if(!response.ok){
                        alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                        
                        return ; 
                    }

                    return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                    //return response.json(); //나머지 경우에 사용
                })
                //fetch 통신 후 실행 영역
                .then((data) => {//data -> controller에서 리턴되는 데이터!
                
                    InfoTestInputTwo();
                    mainTestList(classNum);                    
                })
                //fetch 통신 실패 시 실행 영역
                .catch(err=>{
                    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                    console.log(err);
                });

}

// ######################################## (모달) (과목있음)테스트 정보(만점제외) 조회, 모달로 가져오기  (2) #######################################

    function  mainTestList(classNum){
            // ---------------------------------------- --- 첫번째 방식 -----------------------------------//
            fetch('/test/selectTest', { //요청경로
                method: 'POST',
                cache: 'no-cache',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                //컨트롤러로 전달할 데이터
                body: new URLSearchParams({
                    // 데이터명 : 데이터값
                    'classNum' : classNum

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

                    const sel = document.querySelector('#sel');               
                    sel.innerHTML=``; 
                    let std=``;
                
                    data.forEach(function(testOne, idx){
                        std+=`<option value=${testOne.testNum}>${testOne.testName}</option>`;     
                        });
                
                        sel.insertAdjacentHTML('afterbegin',std); 
                        test_detail_modal.show();           

            })
            //fetch 통신 실패 시 실행 영역
            .catch(err=>{
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });

}






//#################################(모달) 과목 입력시 메인테스트명 선택하여 value 값 꺼내기 ###########################

function changeTest(){    
    const chTestValue = (chTest.options[chTest.selectedIndex].value);
    console.log(9999999);
    console.log(chTestValue);

    if(document.querySelector('input[value="1"]:checked')){
        console.log(12222);    
    }

    else if(document.querySelector('input[value="2"]:checked')) {
        console.log(344444);
        chooseSub(chTestValue);    
    }
    
}   



// ##########################################(모달)과목있음 선택시, // 과목 목록 + 총점 구하기 // (메인, 과목시험 구분) ####################################

function chooseSub(testNumForSub){  
            
            // ------------------- 첫번째 방식 ---------------//
            fetch('/test/selectSubTest', { //요청경로
                method: 'POST',
                cache: 'no-cache',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                //컨트롤러로 전달할 데이터
                body: new URLSearchParams({
                // 데이터명 : 데이터값
                'testNum' : testNumForSub
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
                console.log('44444'+data.testNum2[0].testNum);
                
                // 만약 메인테스트가 0 일때
                if(data.testNum2[0].testMaxScore ==0){
                        const selSub = document.querySelector('#selSub');
                            selSub.innerHTML=``; 
                            let std=``;
                        
                            // 모달 과목조회
                            data.subNames.forEach(function(testSubOne, idx){
                                std+=`<option value=${testSubOne.subTestNum}>${testSubOne.subName}</option>`;     
                                });                       
                                selSub.insertAdjacentHTML('afterbegin',std); 
                            
                            // 모달 총점계산하여 넣기
                                let subSum =0;
                                const subCount = data.subNames.length;

                                    for(let i =0; i <subCount; i++){
                                        subSum+=parseInt(data.subNames[i].subMaxScore);
                                    }
                                document.querySelector('#subTotalMax').innerHTML=subSum;
                            
                            // 과목추가버튼 그리기        
                                const addSubBtn = document.querySelector('#addSubBtn');
                                addSubBtn.innerHTML=``;
                                let stt='';
                                stt=` <input type="button" class="btn btn-secondary btn-sm" id="subNameAdd" value="과목추가" onclick="goInsertSub(`+testNumForSub+`)"
                                style="--bs-btn-hover-border-color:rgb(179, 179, 180);  --bs-btn-border-color:rgb(179, 179, 180); --bs-btn-bg: rgb(179, 179, 180);--bs-btn-hover-bg:rgb(179, 179, 180)">` 
                            
                                addSubBtn.insertAdjacentHTML('afterbegin',stt);
                } 


                // 만약 메인테스트가 0 아니라면
                else if (data.testNum2[0].testMaxScore !=0){

                            // 과목목록 없애기
                                const selSub = document.querySelector('#selSub');           
                                selSub.innerHTML=``;
                                let stf=``;
                                selSub.insertAdjacentHTML('afterbegin',stf);
                                document.querySelector('#subTotalMax').innerHTML ='';

                            // 만약 메인시험종류 에서 단일시험 선택시
                                if(alert('과목설정이 없는 단일시험 입니다. 시험상세 정보를 확인하세요.')){
                                        return; };
                                
                            // 과목추가 기능없는 버튼 그리기            
                                const addSubBtn = document.querySelector('#addSubBtn');
                                addSubBtn.innerHTML=``;
                                let stt=``;
                                stt=`<input type="button" class="btn btn-secondary btn-sm" id="subBtnAdd" value="과목추가"
                                style="--bs-btn-hover-border-color:rgb(179, 179, 180);  --bs-btn-border-color:rgb(179, 179, 180); --bs-btn-bg: rgb(179, 179, 180);--bs-btn-hover-bg:rgb(179, 179, 180);">`;  

                                addSubBtn.insertAdjacentHTML('afterbegin',stt);
                            
                            // 과목추가 기능 없는 버튼 클릭시 경고창
                                const subBtnAdd = document.querySelector('#subBtnAdd');
                                subBtnAdd.addEventListener('click', function(event){
                                    alert('과목설정이 없는 단일시험 입니다. 과목을 추가할 수 없습니다.')
                                });


                }
            })
            //fetch 통신 실패 시 실행 영역
            .catch(err=>{
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });

}






// ###########################(모달) 모달내 과목 저장######################################


function goInsertSub(testNumForSub){
    
                const subName = document.querySelector('#subName').value;
                const subMaxScore = document.querySelector('#subMaxScore').value;


                console.log(55555555556778);
                console.log(subName);    
                console.log(subMaxScore);
                console.log(testNumForSub);
                
                
                if(subName == '' 
                || subMaxScore == '' 
                ){
                    alert('과목입력창을 재확인해주세요!');
                    chTest.value='';
                    return;
                }
                else if(subMaxScore.replace(regex,'') == ''                            
                    ){
                    alert('입력창을 재확인해주세요!');
                    return;
                    }
                

                        
                                    // ------------------- 첫번째 방식 ---------------//
                        fetch('/test/insertSub', { //요청경로
                            method: 'POST',
                            cache: 'no-cache',
                            headers: {
                                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                            },
                            //컨트롤러로 전달할 데이터
                            body: new URLSearchParams({
                            // 데이터명 : 데이터값
                            
                            'subName': subName,
                            'subMaxScore': subMaxScore,
                            'testNum': testNumForSub

                            })
                        })
                        .then((response) => {
                            if(!response.ok){
                                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                                return ;
                            }

                            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                            //return response.json(); //나머지 경우에 사용
                        })
                        //fetch 통신 후 실행 영역
                        .then((data) => {//data -> controller에서 리턴되는 데이터!

                            

                            // 과목+총점조회
                                chooseSub(testNumForSub); 
                                document.querySelector('#subName').value="";
                                document.querySelector('#subMaxScore').value="";  
                                                    
                        })
                        //fetch 통신 실패 시 실행 영역
                        .catch(err=>{
                            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                            console.log(err);
                        });

                    
                
}



// #########################################(모달)모달 내, 등록버튼 클릭시 input 태그 리셋(1) #########################################

function InfoTestInput(){


            const values = ['#testName', '#testMaxScore', '#testDate']

            for(let i = 0; i<values.length; i++){
                let input = document.querySelector(values[i]);
                input.value=input.defaultValue;
            }
}


// #########################################(모달)모달 내, 등록버튼 클릭시 input 태그 리셋(2) #########################################
function InfoTestInputTwo(){
    const values = ['#testName',  '#testDate']

            for(let i = 0; i<values.length; i++){
                let input = document.querySelector(values[i]);
                input.value=input.defaultValue;
            }    

}


 // #########################################(모달) 창닫기 버튼 눌리면 성적목록 리로딩되어 목록 업데이트됨 #########################################

 function updateScorePage(){
    const classNum = document.querySelector('#classNum').value;
 location.href='/test/scoreTeacher?classNum='+classNum;
}




// ######################################### 시험목록 '조회' 클릭시 시험상세정보 조회   #########################################


function goSelectScore(testNum, tag){

    
    console.log(tag); 
    let trTag = tag.parentNode.parentNode;
    let trtags= document.querySelectorAll('.tr-row');
    for(let tr of trtags){tr.style.color="black";} 
    console.log(trTag); 
    trTag.style.color = "blue";

                // ------------------- 첫번째 방식 ---------------//
                fetch('/test/selectScoreList', { //요청경로
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                    },
                    //컨트롤러로 전달할 데이터
                    body: new URLSearchParams({
                    // 데이터명 : 데이터값
                    'testNum':testNum
                    
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
                    
                    // 시험 상제정보 그리기
                        const rowthree = document.querySelector('.rowthree');
                        rowthree.innerHTML='';
                        let str=``;
                        str+= `
                        <div class="col">
                            <div class="row>
                                    <div class="col-10 scoreList-div">                                                                         
                                        <table class="table-blank text-center align-middle tr-rowTwo" style="width:83%; margin-left:10px;">
                                                    <colgroup>
                                                            <col width="10%">
                                                            <col width="30%">
                                                            <col width="20%">
                                                            <col width="20%">
                                                            <col width="20%">
                                                    </colgroup>
                                                    <tr style="font-size: 16px;" class="th-noback">  
                                                        <th>시험번호</th>
                                                        <th>시험명</th>
                                                        <th>시험만점</th>
                                                        <th>시험일자</th>
                                                        <th>시험관리</th>                                                        
                                                    </tr>`                                           
                            data.testSelectList.forEach(function(scoreStu, idx){
                                    str+=`<tr style="font-size: 15px;" class="tr-line yyy">
                                                    <td class="td-line">${scoreStu.testNum}</td>
                                                    <td class="td-line">${scoreStu.testName}</td>
                                                    `;
                                            
                                        
                                            if(scoreStu.testMaxScore ==0){
                        
                                                // 총점계산하여 넣기
                                                let subSum =0;
                                                const subCount = data.subDetailList.length;

                                                for(let i =0; i <subCount; i++){
                                                    subSum+=parseInt(data.subDetailList[i].subMaxScore);
                                                    console.log(subSum); }                                                                                          
                                                str+=`<td class="td-line" id="subTotalD"> ${subSum}</td>`                                                                                                                          
                                            }

                                            else if(scoreStu.testMaxScore !=0){
                                                str+=`<td class="td-line">${scoreStu.testMaxScore}</td>`;
                                            }
                                            
                                            str+=`<td class="td-line">${scoreStu.testDate}</td>
                                                    <td class="td-line">
                                                            <input type="button" class="btn btn-secondary btn-sm listBtn lib"  value="수정" onclick="mainTestChange(${scoreStu.testNum},this)">                               
                                                            <input type="button" class="btn btn-secondary btn-sm listBtn" style="margin-left: 2px;" value="삭제" onclick="deleteTestSC(${scoreStu.testNum})">
                                                                                        
                                                    </td>
                                            </tr>` 
                                // 만약 메인총점 0 아니라면    
                                if(scoreStu.testMaxScore !=0){
                                    str+= `</table>                                                                                      
                                    </div>                                                                                                                    
                                </div>
                                        <div class="row mt-1">
                                            <div class="col-8">
                                                <table class="table-blank text-center align-middle tr-rowTwo" style="width:70%; margin-left:10px; ">
                                                    <col width="20%">
                                                    <col width="25%">
                                                    <col width="20%">
                                                    <col width="20%">                                               
                                                    
                                                    <tr style="font-size: 16px;" class="th-noback">
                                                        <th>과목번호</th>                                    
                                                        <th>과목</th>
                                                        <th>과목만점</th>
                                                        <th>과목관리</th>  
                                                    </tr>
                                                    
                                                    <tr class="tr-sel tr-line"> 
                                                        <td class="td-line" colspan="4">--------------</td>  
                                                    </tr>
                                                </table>
                                            </div>    
                                        </div>`; 

                                    }
                                    // 만약 메인총점 0 이라면
                                    if(scoreStu.testMaxScore ==0){

                                        str+= `</table>                                                                                      
                                        </div>                                                                                                                    
                                    </div>
                                            <div class="row mt-1" >
                                                <div class="col-8" id="subTr">
                                                    <table class="table-blank text-center align-middle tr-rowTwo" style="width:70%; margin-left:10px; ">
                                                        <col width="20%">
                                                        <col width="25%">
                                                        <col width="20%">
                                                        <col width="20%">                                               
                                                        
                                                        <tr style="font-size: 16px;" id="subHead" class="th-noback">
                                                            <th>과목번호</th>                                    
                                                            <th>과목</th>
                                                            <th>과목만점</th>
                                                            <th>과목관리</th>                                                                                                  
                                                        </tr>`

                                                    data.subDetailList.forEach(function(subDetail){ 
                                                            str+=  `<tr class="tr-line" style="font-size: 16px;"  >
                                                                        <td class="td-line" id="subtestNum1">${subDetail.subTestNum}</td>
                                                                        <td class="td-line">${subDetail.subName}</td>  
                                                                        <td class="td-line">${subDetail.subMaxScore}</td>  
                                                                        <td class="td-line" id="subBtnStd">
                                                                                <input type="button" class="btn btn-secondary btn-sm listBtn" id="updateSubBtn" value="수정" onclick="selUpdateSub(${subDetail.subTestNum},this)">                             
                                                                                <input type="button" class="btn btn-secondary btn-sm listBtn" style="margin-left: 2px;" value="삭제" onclick="deleteSubSC(${subDetail.subTestNum}, ${subDetail.testNum})">
                                                                        </td>  
                                                                    </tr>`
                                                    }); 
                                                        
                                                str+= `</table>
                                                    </div>    
                                                </div>
                                            </div>`; 
                                        }
                                    
                                });                           

                                    rowthree.insertAdjacentHTML('afterbegin',str);
                                    str = '';
                })
                //fetch 통신 실패 시 실행 영역
                .catch(err=>{
                    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                    console.log(err);
                });


}

// ###############################(시험상세정보)  메인 테스트 수정 클릭시 시험 1개 조회 ##########################

function mainTestChange(testNum, testBtn){

        const  testDateInput =  testBtn.parentElement.previousElementSibling;
        const  testMaxInput  = testBtn.parentElement.previousElementSibling.previousElementSibling;
        const  testNameInput  = testBtn.parentElement.previousElementSibling.previousElementSibling.previousElementSibling;
        
    if(testBtn.value=='수정'){
                                    // ------------------- 첫번째 방식 ---------------//
                            fetch('/test/selUpdateMainTest', { //요청경로
                                method: 'POST',
                                cache: 'no-cache',
                                headers: {
                                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                                },
                                //컨트롤러로 전달할 데이터
                                body: new URLSearchParams({
                                // 데이터명 : 데이터값
                                'testNum' :testNum
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

                                    testNameInput.innerHTML=`<input type="text" id="newTestName"   name="testName"       value="${data.testName}" style="width:200px;">`;                                                                       
                                    if(data.testMaxScore !=0){
                                        testMaxInput.innerHTML=`<input type="text"  id="newTestMaxScore" name="testMaxScore" value=${data.testMaxScore} style="width:100px;">`;
                                    }                                                                    
                                    testDateInput.innerHTML=`<input type="text"  id="newTestDate" value=${data.testDate} name="testDate" style="width:100px;">`;
    
                                    testBtn.value='저장';  
                            })
                            //fetch 통신 실패 시 실행 영역
                            .catch(err=>{
                                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                                console.log(err);
                            });
    }



    else if(testBtn.value=='저장'){

                            // ------------------- 첫번째 방식 ---------------//
                            fetch('/test/selUpdateMainTest', { //요청경로
                                method: 'POST',
                                cache: 'no-cache',
                                headers: {
                                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                                },
                                //컨트롤러로 전달할 데이터
                                body: new URLSearchParams({
                                // 데이터명 : 데이터값
                                'testNum' :testNum
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

                                if(data.testMaxScore !=0){goUpTestFull(testNum, testBtn);}
                                else if(data.testMaxScore ==0){ goUpTestTwo(testNum, testBtn);}
                            })
                            //fetch 통신 실패 시 실행 영역
                            .catch(err=>{
                                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                                console.log(err);
                            });
    }

}



// ##########################################(시험상세정보) 메인테스트 Full정보 '수정' 버튼 클릭시 ##################################

function goUpTestFull(testNum, testBtn){

        const newTestName  =  document.querySelector('#newTestName').value;
        const newTestMaxScore = document.querySelector('#newTestMaxScore').value;
        const newTestDate = document.querySelector('#newTestDate').value;

    alert('시험목록이 새로고침됩니다. \n 변경된 정보를 확인하세요.');

        // ------------------- 첫번째 방식 ---------------//
        fetch('/test/updateMainTest', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
            // 데이터명 : 데이터값
            'testNum' : testNum,
            'testName' :newTestName,            
            'testMaxScore' : newTestMaxScore,            
            'testDate': newTestDate

            })
        })
        .then((response) => {
            if(!response.ok){
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return ;
            }

            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!            
            showNewMain(testNum, testBtn);           
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

}





// ##########################################(시험상세정보) 메인테스트 만점제외하고 상세정보 수정 ##################################
function goUpTestTwo(testNum, testBtn){

    const newTestName  =  document.querySelector('#newTestName').value;    
    const newTestDate = document.querySelector('#newTestDate').value;

    alert('시험목록이 새로고침됩니다. \n 변경된 정보를 확인하세요.');


    // ------------------- 첫번째 방식 ---------------//
    fetch('/test/updateMainTwo', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
        'testNum' : testNum,
        'testName' :newTestName,                              
        'testDate': newTestDate

        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }

        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!        
        showNewMain(testNum, testBtn);       
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}



// ########################### (시험상세정보) 업데이트 후 보이는 메인테스트 정보 조회 #########################################


function showNewMain(testNum, testBtn){
    
    const  testDateInput =  testBtn.parentElement.previousElementSibling;
    const  testMaxInput  = testBtn.parentElement.previousElementSibling.previousElementSibling;
    const  testNameInput  = testBtn.parentElement.previousElementSibling.previousElementSibling.previousElementSibling;
    
            // ------------------- 첫번째 방식 ---------------//
        fetch('/test/selUpdateMainTest', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
            // 데이터명 : 데이터값
            'testNum' : testNum
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
                testNameInput.innerHTML=`<td class="td-line">${data.testName}</td>`;

                if(data.testMaxScore !=0){
                    testMaxInput.innerHTML=`<td class="td-line">${data.testMaxScore}</td>`;
                }                                    
                
                testDateInput.innerHTML=`<td class="td-line">${data.testDate}</td>`;

            testBtn.value='수정';
            
                //1초후 시험목록 업데이트
                setTimeout(function(){
                location.reload();
                },1000);
                
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

}




// ########################  (시험상세정보) 수정 클릭시  과목 1개 조회 #########################

function selUpdateSub(subTestNum,suBtn){

        const  subMaxInput =  suBtn.parentElement.previousElementSibling;
        const  subNameInput  = suBtn.parentElement.previousElementSibling.previousElementSibling;

    if(suBtn.value=='수정'){
                            // ------------------- 첫번째 방식 ---------------//
                            fetch('/test/selUpdateSubTest', { //요청경로
                                method: 'POST',
                                cache: 'no-cache',
                                headers: {
                                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                                },
                                //컨트롤러로 전달할 데이터
                                body: new URLSearchParams({
                                // 데이터명 : 데이터값
                                'subTestNum':subTestNum
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

                                if(suBtn.value=='수정'){
                                    subNameInput.innerHTML=`<input type="text" id="newSubName"   name="subName"       value=${data.subName} style="width:100px;">`;
                                    subMaxInput.innerHTML=`<input type="text"  id="newSubMaxScore" name="subMaxScore" value=${data.subMaxScore} style="width:100px;">`;
                                
                                    suBtn.value='저장';  
                        
                                }
                            })
                            //fetch 통신 실패 시 실행 영역
                            .catch(err=>{
                                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                                console.log(err);
                            });
    }

    else if(suBtn.value=='저장'){


                const newSubName  =  document.querySelector('#newSubName').value;
                const newSubMaxScore = document.querySelector('#newSubMaxScore').value;

                // ------------------- 첫번째 방식 ---------------//
                fetch('/test/updateSubTest', { //요청경로
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                    },
                    //컨트롤러로 전달할 데이터
                    body: new URLSearchParams({
                    // 데이터명 : 데이터값
                    'subTestNum' : subTestNum,
                    'subName' :newSubName,
                    'subMaxScore' : newSubMaxScore

                    })
                })
                .then((response) => {
                    if(!response.ok){
                        alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                        return ;
                    }

                    return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                    //return response.json(); //나머지 경우에 사용
                })
                //fetch 통신 후 실행 영역
                .then((data) => {//data -> controller에서 리턴되는 데이터!                   
                    showNewSub(subTestNum, suBtn);
                    
                })
                //fetch 통신 실패 시 실행 영역
                .catch(err=>{
                    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                    console.log(err);
                });
    }

};

// ########################  (시험상세정보) 수정 클릭시  업데이트 후 과목 정보그리기 #########################
function showNewSub(subTestNum, suBtn){

    const  subMaxInput =  suBtn.parentElement.previousElementSibling;
    const  subNameInput  = suBtn.parentElement.previousElementSibling.previousElementSibling;

            // ------------------- 첫번째 방식 ---------------//
        fetch('/test/selUpdateSubTest', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
            // 데이터명 : 데이터값
            'subTestNum' : subTestNum
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
            console.log(data)
                subNameInput.innerHTML=`<td class="td-line">${data.subName}</td>`;
                subMaxInput.innerHTML=`<td class="td-line">${data.subMaxScore}</td>`;

                suBtn.value='수정';            
                showUpSubMax(data.testNum);
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });


}

// ######################## (시험상세정보) 과목업데이트 후 보여지는 메인 테스트 만점 업뎃 ########################

function  showUpSubMax(testNum){


            // ------------------- 첫번째 방식 ---------------//
            fetch('/test/selectScoreList', { //요청경로
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
        
                    // 총점계산하여 넣기
                    let subSum =0;
                    const subCount = data.subDetailList.length;

                    for(let i =0; i <subCount; i++){
                        subSum+=parseInt(data.subDetailList[i].subMaxScore);
                        console.log(subSum);
                    }
                    document.querySelector('#subTotalD').innerHTML=subSum;

            })
            //fetch 통신 실패 시 실행 영역
            .catch(err=>{
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });

}

// #################################### (시험상세정보) 과목 업데이트 후,  비동기 과목 목록 + 총점 조회
function  updateSubList(testNum){

    // ------------------- 첫번째 방식 ---------------//
    fetch('/test/selectSubTest', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
        'testNum' : testNum

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
            const subTr =document.querySelector('#subTr')
            subTr.innerHTML='';
            let str=''; 
            
            str+= `<table class="table-blank text-center align-middle tr-rowTwo" style="width:70%; margin-left:10px; ">
                                        <col width="20%">
                                        <col width="25%">
                                        <col width="20%">
                                        <col width="20%">                                               
                                        
                                        <tr style="font-size: 16px;" id="subHead" class="th-noback">
                                            <td class="td-line"><strong>과목번호</strong></td>                                    
                                            <td class="td-line"><strong>과목</strong></td>
                                            <td class="td-line"><strong>과목만점</strong></td>
                                            <td class="td-line"><strong>과목관리</strong></td>                                                                                                 
                                        </tr>`

                                    data.subNames.forEach(function(subDetail){ 
                                            str+=   `<tr style="font-size: 16px;" class="tr-line" >
                                                        <td class="td-line" id="subtestNum1">${subDetail.subTestNum}</td>
                                                        <td class="td-line">${subDetail.subName}</td>  
                                                        <td class="td-line">${subDetail.subMaxScore}</td>  
                                                        <td class="td-line" id="subBtnStd">
                                                                <input type="button" class="btn btn-secondary btn-sm listBtn" id="updateSubBtn" value="수정" onclick="selUpdateSub(${subDetail.subTestNum},this)">                             
                                                                <input type="button" class="btn btn-secondary btn-sm listBtn" style="margin-left: 2px;" value="삭제" onclick="deleteSubSC(${subDetail.subTestNum}, ${subDetail.testNum})">
                                                            </td>  
                                                    </tr>`
                                                }); 
                                        
                                    str+= `</table>`    
                subTr.insertAdjacentHTML('afterbegin',str);
                showUpSubMax(testNum);

        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}




// ############################## (시험상세정보) 메인시험 삭제 ####################################

function deleteTestSC(testNum){

    console.log(testNum);
    if(confirm('해당 시험에 있는 모든 정보가 삭제됩니다. 계속 진행하시겠습니까?')){
        location.href='/test/deleteTest?testNum='+ testNum +'&classNum='+classNum; 
        alert("정상적으로 삭제되었습니다.");
	}else{
		alert("삭제실패하였습니다.");
	}

}


// ################################ (시험상세정보) 과목 삭제 ####################################

function deleteSubSC(subTestNum, testNum){

    if(confirm('해당 과목에 있는 모든 정보가 삭제됩니다. 계속 진행하시겠습니까?')){
        // location.href='/test/deleteSub?subTestNum='+ subTestNum +'&classNum='+classNum;  
                      // ------------------- 첫번째 방식 ---------------//
                      fetch('/test/deleteSub', { //요청경로
                        method: 'POST',
                        cache: 'no-cache',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                        },
                        //컨트롤러로 전달할 데이터
                        body: new URLSearchParams({
                        // 데이터명 : 데이터값
                        'subTestNum' :subTestNum,
                        'classNum' : classNum
        
                        })
                    })
                    .then((response) => {
                        if(!response.ok){
                            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                            return ;
                        }
        
                        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                        //return response.json(); //나머지 경우에 사용
                    })
                    //fetch 통신 후 실행 영역
                    .then((data) => {//data -> controller에서 리턴되는 데이터!        
                        alert("정상적으로 삭제되었습니다.");
                        updateSubList(testNum);  
                    })
                    //fetch 통신 실패 시 실행 영역
                    .catch(err=>{
                        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                        console.log(err);
                    });
	}    
    else{ alert("삭제실패하였습니다."); }
}



// #################################### (단일 시험) 등록 또는 (과목별 시험) 등록 페이지 이동 컨트롤러 ####################################
function goSelectSub(testNum, classNum, testMaxScore){

    if(testMaxScore==0){  location.href='/test/goTestN?testNum='+ testNum +'&classNum='+classNum;  }
    else if(testMaxScore!=0){ location.href='/test/goInputScore?testNum='+ testNum; }
        
}





