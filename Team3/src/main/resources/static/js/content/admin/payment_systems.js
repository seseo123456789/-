
// 수강목록 조회 (복수 결제 시)
function findClasses(){

    const classes_modal_1 = new bootstrap.Modal('#classes-modal-1');

    const memberId = document.querySelector('input[name="memberId"]').value;
    const memberRoll = document.querySelector('input[name="memberRoll"]').value;
    
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
        const modal_tbody_1 = document.querySelector('.class-tbody-tag');
        
        modal_tbody_1.innerHTML = '';
        modal_tbody_1.replaceChildren();


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
                        <td>${e.classSdate} ~ ${e.classEdate}</td>
                            <input type="hidden" name="classNum" value="${e.classNum}">
                            <input type="hidden" name="memberId" value="${e.teacherVO.memberVO.memberId}">
                        </tr>`;
        });


        modal_tbody_1.insertAdjacentHTML('afterbegin', str);

        classes_modal_1.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });




}