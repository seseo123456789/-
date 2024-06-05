function passwordInput(){
    const pwiDiv = document.querySelector("#passwordInput");
    pwiDiv.innerHTML = '';
    let str = '';
    str += `
    <div class="col-3">
    <div class="row">
        <div class="col">
            <input type="password" id="pwvalue" class="form-control">
        </div>
    </div>
    <div class="row">
        <div class="col" style="text-align: right;">
            <span style="color: red; font-size: 0.8rem;">*임시 비밀번호도 가능</span>
        </div>
    </div>
</div>
<div class="col-3">
    <div class="row">
        <div class="col-4">
            <input type="button" value="확인" class="btn btn-outline-success" onclick="pw_check()">
        </div>
        <div class="col-7" style="text-align: start;">
            <input type="button" value="취소" class="btn btn-outline-secondary" onclick="goLoad()">
        </div>
    </div>
    <div class="row">
        <div class="col" style="text-align: start;">
    
        </div>
    </div>
</div>
<div class="col-6"></div>
    `;
    pwiDiv.insertAdjacentHTML("afterbegin",str);
}

function goLoad(){
    location.reload(true);
}


function pw_check(){
    const pwvalue = document.querySelector("#pwvalue").value;
    fetch('/member/pwCheck', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
            memberPw : pwvalue
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
        if(data){
            const pwcDiv = document.querySelector("#passwordChanger");
            const pwiDiv = document.querySelector("#passwordInput");
            pwcDiv.innerHTML = '';
            pwiDiv.innerHTML = '';
            let str = '';
            str +=`
            <div class="row mb-2" style="margin-top: 20px;">
                <div class="col" style="margin-top: 25px; font-size:20px;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-right-text-fill text-success" viewBox="0 0 16 16">
                    <path d="M2 1a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h9.586a2 2 0 0 1 1.414.586l2 2V2a1 1 0 0 0-1-1zm12-1a2 2 0 0 1 2 2v12.793a.5.5 0 0 1-.854.353l-2.853-2.853a1 1 0 0 0-.707-.293H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z"/>
                    <path d="M3 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5M3 6a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 6m0 2.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5"/>
                    </svg> <strong>비밀번호 변경 </strong>
                </div>
            </div>
            <table class="table">
                <colgroup>
                    <col width="40%">
                    <col width="60%">
                </colgroup>
                
                <tr>
                    <th>새 비밀번호</th>
                    <td><input type="password" id="newPw" class="form-control"></td>
                </tr>
                <tr>
                    <th>비밀번호 확인</th>
                    <td><input type="password" id="chkPw" class="form-control"></td>
                </tr>
                <tr>
                    <td colspan="2" id="readyToNextButton" style="text-align: center;">
                        <input type="button" value="일치 확인" class="btn btn-outline-secondary" onclick="passwordEqualsCheck()">
                        <input type="button" value="취소" class="btn btn-outline-secondary" onclick="goLoad()">
                    </td>
                </tr>
            </table>
            `;
            pwcDiv.insertAdjacentHTML("afterbegin",str);
        }
        else{
            alert("비밀번호가 일치 하지않습니다. 다시한번 확인해주세요");
            return;
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function passwordEqualsCheck(){
    const aPw = document.querySelector("#newPw");
    const bPw = document.querySelector("#chkPw");
    if(aPw.value == bPw.value){
        alert("비밀번호가 일치합니다.");
        const div = document.querySelector("#readyToNextButton");
        div.innerHTML = ``;
        let str = ``;
        str += `<input type="button" value="비밀번호 변경" class="btn btn-outline-success" onclick="realChange()">`;
        div.insertAdjacentHTML("beforeend",str);
        aPw.readOnly=true;
        bPw.readOnly=true;
    }
    else{
        alert("두 비밀번호가 일치하지 않습니다. 다시 입력해 주십시오.");
        return;
    }
}

function realChange(){
    if(confirm('변경하시겠습니까?')){
        const Pw = document.querySelector("#newPw").value;
        fetch('/member/updatePassword2', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
               // 데이터명 : 데이터값
                memberPw : Pw
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
            alert('비밀번호가 변경되었습니다. 세션에서 로그아웃됩니다.');
            location.href='/member/logout';
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }
}