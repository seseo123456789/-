function findPw(btn){
    const memberId = document.querySelector('#member_id').value;
    const memberName = document.querySelector('#member_name').value;
    btn.disabled = true;
    btn.innerHTML = '';
    let btnstr = `<span class="spinner-border spinner-border-sm" aria-hidden="true"></span>
                    <span role="status">비밀번호 찾는 중...</span>`;
    btn.insertAdjacentHTML("afterbegin",btnstr); 
    fetch('/member/findPwFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'memberId' : memberId,
           'memberName' : memberName
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
            btn.innerHTML = '';
            let btnstrr = `<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-check-lg" viewBox="3 4 11 11">
            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425z"/>
            </svg>`;
            btn.insertAdjacentHTML("afterbegin",btnstrr);
            document.querySelector("#failChecker").innerHTML = '';
            let str = '';
            str += `가입하신 Email로 임시비밀번호를 전송했습니다.`;
            document.querySelector("#failChecker").insertAdjacentHTML('afterbegin',str);
        }
        else{
            btn.innerHTML = '비밀번호 찾기';
            btn.disabled = false;
            document.querySelector("#failChecker").innerHTML = '';
            let str = '';
            str += `아이디와 비밀번호를 확인해주세요.`;
            document.querySelector("#failChecker").insertAdjacentHTML('afterbegin',str);
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}