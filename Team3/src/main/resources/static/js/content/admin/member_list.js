// export.updateMemberInfo =updateMemberInfo;

const IMP = window.IMP;
IMP.init('imp48362627');

const updateMemberId = document.querySelector('input[type="hidden"]').value;
const memberId_tag = document.querySelector('input[name="memberId"]').value;

if(updateMemberId != ""){
    memberDetail(updateMemberId);
}
    
// 클릭한 회원 상세 정보 조회
function memberDetail(memberId){
    const member_detail_modal = new bootstrap.Modal('#member-detail-modal');

    fetch('/admin/memberDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
           memberId : memberId
        })
    })
    .then((response) => {
        // return response.text(); //나머지 경우에 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        // 모달 상세 정보 하단
        const modal_tbodya = document.querySelector('.tbody-tagaa');

        modal_tbodya.innerHTML = '';
        modal_tbodya.replaceChildren();


        let str = '';
        str +=  `
                    <tr>
                        <td class="table-active">이름(ID)</td>
                        <td id="modal-memberName">
                            <input class="form-control" type="text" name="memberName" value="${data.memberName}"> 
                        </td>
                        <td class="table-active">나이</td>
                        <td id="modal-memberBrith">${data.memberAge}</td>
                    </tr>
                    <tr>
                        <td class="table-active">성별</td>
                        <td colspan="3" id="modal-memberName">
                    <div class="row">`;

                    if(data.memberGender == '남'){
                        str += `
                                <div class="offset-2 col-4">
                                    <input class="form-check-input" type="radio" name="memberGender" disabled checked value="남">남자
                                </div>
                                <div class="col-4">
                                    <input class="form-check-input" type="radio" name="memberGender" disabled value="여">여자
                                </div>
                                `;
                    }
                    else{
                        str += ` <div class="offset-2 col-4">
                        <input class="form-check-input" type="radio" name="memberGender" disabled value="남">남자
                    </div>
                    <div class="col-4">
                        <input class="form-check-input" type="radio" name="memberGender" disabled checked value="여">여자
                    </div>
                                `;
                    }
                        
                        
        str +=     `
                    </div>
                    </td>
                </tr>
                    <tr>
                        <td class="table-active">연락처</td>
                        <td id="modal-memberTel">
                        <input class="form-control" type="text" name="memberTel" value="${data.memberTel}"></td>
                        <td class="table-active">메일</td>
                        <td id="modal-memberRoll">
                        <input class="form-control memberEmail-input" type="text" name="memberEmail" value="${data.memberEmail}"></td>
                    </tr>
                    <tr>
                        <td rowspan="2"class="table-active">주소</td>
                        <td colspan="2"><input type="text" class="form-control" id="postCode" name="postCode" value="${data.postCode}" readonly></td>
                        <td>
                            <div class="offset-3 d-grid"><input type="button" class="btn btn-primary" value="주소검색" onclick="addrModal()"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" id="modal-memberAddr"><input type="text" class="form-control" id="roadAddr" value="${data.memberAddr}" name="memberAddr" readonly></td>
                    </tr>
                    <tr>
                        <td class="table-active">상세 주소</td>
                        <td colspan="3"> 
                        <input type="text" class="form-control" value="${data.addrDetail}" name="addrDetail">
                        </td>
                    </tr>
                    <input type="hidden" name="memberId" value="${data.memberId}">

                `;


        modal_tbodya.insertAdjacentHTML('afterbegin', str);


        member_detail_modal.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

// 주소 변경 시, 주소 입력창 띄우기
function addrModal(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector('#postCode').value = data.zonecode;
            document.querySelector('#roadAddr').value = data.roadAddress;
          }
        }).open();
}

// 선택한 회원의 인적 사항 변경 버튼 클릭 시 confirm 실행
function updateMemberInfo(){
    const memberName = document.querySelector('input[name="memberName"]').value;
    const memberTel = document.querySelector('input[name="memberTel"]').value;
    const memberEmail = document.querySelector('input[name="memberEmail"]').value;
    const memberAddr = document.querySelector('input[name="memberAddr"]').value;
    const addrDetail = document.querySelector('input[name="addrDetail"]').value;
    const postCode = document.querySelector('input[name="postCode"]').value;

    const form_tag = document.querySelector('.changePersonalInfo');

    if(confirm(`
    이름: ${memberName} 
    연락처: ${memberTel}
    메일: ${memberEmail}
    주소: ${memberAddr}
          ${addrDetail}
    우편번호: ${postCode}
    로 수정하시겠습니까?`
    )){
        form_tag.submit();
    }
    
}

// 해당 회원의 권한 변경
function changeRoll(selectedTag, memberId){
    let str_roll = ''
    if(selectedTag.value == 1){
        str_roll = '학생';
    }
    else if(selectedTag.value == 2){
        str_roll = '강사';
    }
    else{
        str_roll = '관리';
    }

    if(confirm(`${str_roll}(으)로 변경하시겠습니까?`)){
        
        fetch('/admin/updateRoll', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: JSON.stringify({
            // 데이터명 : 데이터값
            memberId : memberId,
            memberRoll : selectedTag.value
            })
        })
        .then((response) => {
            // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            selectedTag.innerHTML = '';
            let button_tag = selectedTag.parentElement.parentElement.parentElement.nextElementSibling;
            button_tag.innerHTML = '';
            let str2 = ``;
            if (data.memberRoll == 1){
                str2 = `
                    <option value="1" selected>학생</option>
                    <option value="2" >강사</option>
                    <option value="3" >관리</option>
                `;
            } 
            else if(data.memberRoll == 2){
                str2 = `
                    <option value="1" >학생</option>
                    <option value="2" selected>강사</option>
                    <option value="3" >관리</option>
                `;
            }
            else{
                str2 =`
                    <option value="1">학생</option>
                    <option value="2" >강사</option>
                    <option value="3" selected >관리</option>
                `;
            }
            
            let str3 = '';
            if(data.memberRoll == 1){
                str3 += `
                    <button type="button" class="btn btn-outline-primary"
                    onclick="memberDetail(${data.memberId})">인적사항</button>
                    <button type="button" class="btn btn-outline-success"
                    onclick="showClasses(${data.memberId}, ${data.memberRoll})">수강목록</button>
                    <button th:if="${data.memberRoll == 1}" type="button" class="btn btn-outline-secondary"
                    th:onclick="insertClass([[${data.memberId}]])">강의신청</button>
                    <button th:if="${data.memberRoll == 1}" type="button" class="btn btn-outline-secondary"
                    th:onclick="goSearch([[${data.memberId}]])">성적조회</button>
                        `;
            }
            else{
                str3 += `
                    <button type="button" class="btn btn-outline-primary"
                    onclick="memberDetail(${data.memberId})">인적사항</button>
                    <button type="button" class="btn btn-outline-success"
                    onclick="showClasses(${data.memberId}, ${data.memberRoll})">강의목록</button>
                    `;
            }
            selectedTag.insertAdjacentHTML("afterbegin", str2);
            button_tag.insertAdjacentHTML("afterbegin", str3);
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }
    
}

// 수강목록 조회
function showClasses(memberId, memberRoll){
    const classes_modal = new bootstrap.Modal('#classes-modal');

    fetch('/admin/showClass', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
           memberId : memberId ,
           memberRoll : memberRoll
        })
    })
    .then((response) => {
        // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        // 모달 상세 정보 하단
        const modal_tbody = document.querySelector('.class-tbody-tag');
        
        modal_tbody.innerHTML = '';
        modal_tbody.replaceChildren();


        let str = '';
        str +=  `
                    <tr>
                        <td class="table-active">No</td>
                        <td class="table-active">강의명</td>
                        <td class="table-active">담당 강사</td>
                        <td class="table-active">강의 기간</td>
                    </tr>`;

        data.forEach(function (e, idx) {
            str += `<tr>
                        <td>${idx + 1}</td>
                        <td>${e.className}</td>                    
                        <td>${e.teacherVO.teacherName}</td>
                        <td>${e.classSdate} ~ ${e.classEdate}</td>`;
            if(memberRoll == 1){
                str += `
                <input type="hidden" name="classNum" value="${e.classNum}">
                <input type="hidden" name="memberId" value="${e.teacherVO.memberVO.memberId}">`;

            } else {
                str += `  `;
            }           
            str += `</tr>`;
        });


        modal_tbody.insertAdjacentHTML('afterbegin', str);

        classes_modal.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    

}



// 수강신청 페이지 이동
function insertClass(memberId){
    const reg_class = new bootstrap.Modal('#reg-class-modal');
    const selectMemberId = memberId;
    fetch('/admin/goRegClass', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
            memberId : memberId
        })
    })
    .then((response) => {
        // return response.text(); //나머지 경우에 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        // 모달 상세 정보 하단
        const modal_tbodyy = document.querySelector('.tbody-tag');

        modal_tbodyy.innerHTML = '';
        modal_tbodyy.replaceChildren();

        let str = '';
        
        str += `<input type="hidden" name="memberId" id="memberId" value="${selectMemberId}">
                `;
        data.forEach(function (e){
            let pay = e.classPayment.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            str +=  `
                <tr>
                    <td>
                        <input type="checkbox" name="classNum" value="${e.classNum}">
                    </td>
                    <td>${e.className} 반
                    </td>
                    <td>${e.teacherVO.teacherName}</td>
                    <td>${e.classSdate}~${e.classEdate}</td>
                    <td>${pay} 원</td>
                    <td>${e.stuCnt}/${e.classPersonnel}</td>
                    <td>
                        <input type="button" class="btn btn-outline-success" value="신청" onclick="chkDuple(this,${e.classNum})">
                    </td>
                </tr>
                `;
        })
        
        modal_tbodyy.insertAdjacentHTML('afterbegin', str);
        reg_class.show();
    
    });

}

// 수강 중복 체크
function chkDuple(selected, classNum){
    const memberId = document.querySelector('#memberId').value;

    fetch('/admin/chkDuple', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
           memberId : memberId,
           classNum : classNum
        })
    })
    .then((response) => {
        return response.text();
        // return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        if(data > 0){
            alert('이미 수강 신청한 반입니다.');
        
        } else {
            regClass(memberId, classNum);
        }
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}

// 수강 신청한 여러 반 클릭 시 실행
function chkDuples(){
    const checks = document.querySelectorAll('input[type="checkbox"]:checked');
    const memberId = document.querySelector('#memberId').value;

    const chkArr = [];
    if(checks.length > 0){
        for(const chk of checks){
            if(chk.checked == true){
                a = {
                    classNum : chk.value,
                    'memberId' : memberId,
                }
                chkArr.push(a);

            }
        }

        fetch('/admin/chkDuples', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: JSON.stringify(
                chkArr
            )
        })
        .then((response) => {
            return response.text();
            // return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            if(data == 0){
                reqSomePay();
            } else {
                return alert('이미 수강신청한 반입니다.');
            }
            
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    } else {
        alert('수강신청할 반을 선택하세요.');
    }
}
// 수강 신청 버튼 클릭 시 실행
function regClass(memberId, classNum){
    alert(memberId, classNum);
    
    if(confirm(`결제하시겠습니까?`)){
        fetch('/admin/goPayment', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: JSON.stringify({
            // 데이터명 : 데이터값
            memberId : memberId,
            classNum : classNum

            })
        })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            if(data != null){
                IMP.request_pay({
                    pg : 'kakaopay',
                    pay_method : "card",
                    merchant_uid : 'merchant_' + new Date().getTime(),
                    name : `${data.className}`, // className
                    amount : `${data.classPayment}`, // operPay
                    buyer_email : '', // memberEmail
                    buyer_name : `${data.teacherVO.memberVO.memberName}`, // memberName
                    buyer_tel : `${data.teacherVO.memberVO.memberTel}`,
                    buyer_addr : `${data.teacherVO.memberVO.memberAddr + data.teacherVO.memberVO.addrDetail}`,
                    buyer_postcode : `${data.teacherVO.memberVO.postCode}`


                }, function (rsp){ // callback
                    console.log(rsp);
                    let msg = '';
                    if(rsp.success){
                        msg = '결제가 완료되었습니다.';
                        alert(msg);
                        location.href = `/admin/successPayment?operNum=${data.operatorVOList[0].operNum}`;
                    } else {

                        fetch('/admin/deleteOperator', { //요청경로
                            method: 'POST',
                            cache: 'no-cache',
                            headers: {
                                'Content-Type': 'application/json; charset=UTF-8'
                            },
                            //컨트롤러로 전달할 데이터
                            body: JSON.stringify({
                               // 데이터명 : 데이터값
                               memberId : memberId,
                               classNum : classNum
                            })
                        })
                        .then((response) => {
                            return response.text();
                            // return response.json(); //나머지 경우에 사용
                        })
                        //fetch 통신 후 실행 영역
                        .then((data) => {//data -> controller에서 리턴되는 데이터!
                            msg += '결제에 실패했습니다.';
                            msg += '에러내용:' + rsp.error_msg;
                            alert(msg);
                        })
                        //fetch 통신 실패 시 실행 영역
                        .catch(err=>{
                            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                            console.log(err);
                        });
                        
                    }
                });
            } else {
                alert('이미 수강 신청한 학생입니다.');
            }
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    

    }
}

// 선택한 여러 반 수강 신청
function reqSomePay(){
    const checks = document.querySelectorAll('input[type="checkbox"]:checked');
    const memberId = document.querySelector('#memberId').value;
    const classNum = 0;
    const chkArr = [];
    for(const chk of checks){
        if(chk.checked == true){
            a = {
                classNum : chk.value,
                'memberId' : memberId,
            }
            chkArr.push(a);

        }
    }


    if(confirm(`결제하시겠습니까?`)){
        fetch('/admin/goPayments', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: JSON.stringify(chkArr)
        })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data);

            let classCnt = 0;
            let totalPay = 0;
            let operArray = [];
            let operNumArr = [];

            data.forEach(function(e, idx){
                totalPay += e.classPayment;
                classCnt = idx;
                dt = {
                    classNum : e.classNum,
                    memberId : e.teacherVO.memberVO.memberId,
                    operNum : e.operatorVOList[0].operNum
                }
                operArray.push(dt);
                operNumArr.push(e.operatorVOList[0].operNum);
            })
            
            IMP.request_pay({ 
                pg : 'kakaopay',
                pay_method : "card",
                merchant_uid : 'merchant_' + new Date().getTime(),
                name : `${data[0].className} 외 ${classCnt}`, // className
                amount : `${totalPay}`, // operPay
                buyer_email : `${data[0].teacherVO.memberVO.memberEmail}`, // memberEmail
                buyer_name : `${data[0].teacherVO.memberVO.memberName}`, // memberName
                buyer_tel : `${data[0].teacherVO.memberVO.memberTel}`,
                buyer_addr : `${data[0].teacherVO.memberVO.memberAddr + data[0].teacherVO.memberVO.addrDetail}`,
                buyer_postcode : `${data[0].teacherVO.memberVO.postCode}`

            }, function (rsp){ // callback
                console.log(rsp);
                if(rsp.success){
                    const msg = '결제가 완료되었습니다.';
                    alert(msg);

                    fetch('/admin/checkReceipt', { //요청경로
                        method: 'POST',
                        cache: 'no-cache',
                        headers: {
                            'Content-Type': 'application/json; charset=UTF-8'
                        }, 
                        //컨트롤러로 전달할 데이터
                        body: JSON.stringify(operArray)
                    })
                    .then((response) => {
                        return response.text();
                        // return response.json(); //나머지 경우에 사용
                    })
                    //fetch 통신 후 실행 영역
                    .then((data) => {//data -> controller에서 리턴되는 데이터!
                        location.href=`/admin/successPayments?operNumList=${operNumArr}`;
                    })
                    //fetch 통신 실패 시 실행 영역
                    .catch(err=>{
                        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                        console.log(err);
                        
                    });

                } else {
                    let msg = '결제에 실패했습니다.';
                    msg += '에러내용: ' + rsp.error_msg;
                    alert(msg);
                    fetch('/admin/deleteOperators', { //요청경로
                        method: 'POST',
                        cache: 'no-cache',
                        headers: {
                            'Content-Type': 'application/json; charset=UTF-8'
                        },
                        //컨트롤러로 전달할 데이터
                        body: JSON.stringify(chkArr)
                    })
                    .then((response) => {
                        return response.text();
                        // return response.json(); //나머지 경우에 사용
                    })
                    //fetch 통신 후 실행 영역
                    .then((data) => {//data -> controller에서 리턴되는 데이터!

                    })
                    //fetch 통신 실패 시 실행 영역
                    .catch(err=>{
                        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                        console.log(err);
                    });
                    
                }
            });
            
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    
    }
  
}

// 성적조회 페이지 이동
function goSearch(memberId){
    location.href="/test/stuTestAdmin?memberId="+ memberId;
}