
//약관 동의 시 회원가입 양식 나타남
function agree() {
  document.getElementById("registrationForm").style.display = "block";
}

//약관 비동의 시 회원가입 양식 사라짐
function disAgree() {
  document.getElementById("registrationForm").style.display = "none";
}

//회원가입 유효성 검사(1)
function result(){
  const check1 = /[^a-zA-Z0-9]/g; //영어, 숫자만
  const check2 = /[가-힣]/g; //한글만
  const check3 = /\d/g; //숫자만
  const check4 = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;

  const id = document.querySelector('#checkID').value;
  const pw = document.querySelector('#newPw').value;
  const name = document.querySelector('#name').value;
  const phone1 = document.querySelector('#phone1').value;
  const phone2 = document.querySelector('#phone2').value;
  const email = document.querySelector('#email').value;

  // ID 검사 (영어, 숫자만)
  if (id != '' && id != null && check1.test(id)) {
    alert('ID는 영어와 숫자만 사용할 수 있습니다.');
    return;
  }

  // 이메일 검사 (영어, 숫자만)
  if (email != null && email != '' && check1.test(email)) {
    alert('이메일은 영어와 숫자만 사용할 수 있습니다.');
    return;
  }

  //비밀번호 (영어, 숫자만) + 특수문자 포함
  if (pw != null && pw != '' && (!check1.test(pw) || !check4.test(pw))) {
    alert('비밀번호는 영어, 숫자, 특수문자(@, #, $, %)를 최소 하나씩 포함해야 합니다.');
    return;
  }

  // 이름 검사 (한글만)
  if (name != null && name != '' && !check2.test(name)) {
    alert('이름은 한글만 입력해야 합니다.');
    return;
  }

  // 전화번호 검사 (숫자만)
  if (phone1 != null && phone1 != '' && !check3.test(phone1)) {
    alert('전화번호는 숫자만 입력해야 합니다.');
    return;
  }
  if (phone2 != null && phone2 != '' && !check3.test(phone2)) {
    alert('전화번호는 숫자만 입력해야 합니다.');
    return;
  }
  
}


//회원가입 유효성 검사(2) 아이디 길이 제한
function idMax(){
  const idDiv = document.querySelector('#checkID');
  const memberId = document.querySelector('#checkID').value;
  if(memberId.length > 20){
    alert('ID를 20자 이내로 입력해주세요.')
    idDiv.value = '';
  }
  else if(memberId.length < 5){
    alert('ID를 5자 이상 입력해주세요.')
  }
}

//회원가입 유효성 검사(3) 비밀번호 확인
function pwCheck(){
  const pwDiv = document.querySelector('#chkPw');
  const aPw = document.querySelector("#newPw").value;
  const bPw = document.querySelector("#chkPw").value;
  if(aPw != bPw){
    alert('두 비밀번호가 일치하지 않습니다. 다시 입력해 주십시오.')
    pwDiv.value = '';
  }
}

//회원가입 유효성 검사(4) 전화번호 길이 제한
function phoneCheck1(){
  const phone1Div = document.querySelector('#phone1');
  const phone1 = document.querySelector('#phone1').value;
  if(phone1.length != 4){
    alert('전화번호를 올바르게 입력해주세요.')
    phone1Div.value = '';
  }
}
function phoneCheck2(){
  const phone2Div = document.querySelector('#phone2');
  const phone2 = document.querySelector('#phone2').value;
  if(phone1.length != 4){
    alert('전화번호를 올바르게 입력해주세요.')
    phone2Div.value = '';
  }
}

//아이디 중복 확인
function idCheck(){
  const memberId = document.querySelector('#checkID').value;
  fetch('/member/idCheckFetch', { //요청경로
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    //컨트롤러로 전달할 데이터
    body: new URLSearchParams({
      // 데이터명 : 데이터값
        'memberId' : memberId
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
  .then((result) => {//data -> controller에서 리턴되는 데이터!
    if(result == 0){
      alert('사용 가능한 아이디입니다.')
    }
    else{
      alert('중복되는 아이디입니다. \n 다른 아이디를 입력해주세요.')
    }
  })
  //fetch 통신 실패 시 실행 영역
  .catch(err=>{
    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    console.log(err);
  });
}

// 주소 검색
function searchAddress(){
    new daum.Postcode({
      oncomplete: function(data) {
          document.querySelector('#postCode').value = data.zonecode;
          document.querySelector('#roadAddr').value = data.roadAddress;
        }
    }).open();
  }

// 회원가입 유효성 검사(5) - 필수 입력사항 확인
function join(){
    const id = document.querySelector('#checkID').value;
    const pw = document.querySelector('#newPw').value;
    const name = document.querySelector('#name').value;
    const phone1 = document.querySelector('#phone1').value;
    const phone2 = document.querySelector('#phone2').value;
    const email = document.querySelector('#email').value;
    const birth = document.querySelector('#birth').value;

    if(id == '' && id == null){
      alert('ID는 필수 입력 사항입니다. \n ID를 입력하세요.');  
      return false;
    }

    if(pw == '' && pw == null){
      alert('비밀번호는 필수 입력 사항입니다. \n 비밀번호를 입력하세요.');  
      return false;
    }

    if(name == '' && name == null){
      alert('이름은 필수 입력 사항입니다. \n 이름을 입력하세요.');  
      return false;
    }

    if(phone1 == '' && phone1 == null){
      alert('전화번호는 필수 입력 사항입니다. \n 전화번호를 입력하세요.');  
      return false;
    }

    if(phone2 == '' && phone2 == null){
      alert('전화번호는 필수 입력 사항입니다. \n 전화번호를 입력하세요.');  
      return false;
    }

    if(email == '' && email == null){
      alert('E-mail은 필수 입력 사항입니다. \n E-mail을 입력하세요.');  
      return false;
    }

    if(birth == ''){
      alert('생년월일은 필수 입력 사항입니다. \n 생년월일을 입력하세요.');  
      return false;
    }


    return true;
}


//유효성 검사 통과 후 회원가입 완료 처리
function submitJoin() {
  if(join()){
    const post = document.querySelector('form');
    post.submit();
    alert('회원가입이 완료되었습니다.');
  }
}
    