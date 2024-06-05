const teacherCode = document.querySelector('#updateTeacherNum').value;

// console.log(teacherCode);

if(teacherCode != 0){
    teacherInfo(teacherCode, className);
}
function teacherInfo(teacherCode, className){

    fetch('/admin/selectTeacher', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
           teacherNum : teacherCode
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!

        const make_spot = document.querySelector('.teacher-list-div');

        make_spot.innerHTML = '';

        let str = '';
        str += `
            <div class="row">
                <div class="col-2 text-center">강사명</div>
                <div class="col-10 d-grid">
                    <input class="form-control" type="text" value="${data[0].teacherVO.memberVO.memberName}">
                </div>
            </div>

            <div class="row mt-2">
                <div class="col">
                    <table class="table align-middle text-center">
                        <colgroup>
                            <col width="20%">
                            <col width="20%">
                            <col width="30%">
                            <col width="30%">
                        </colgroup>
                        <thead class="table-success">
                            <tr>
                                <td>No</td>
                                <td>반 이름</td>
                                <td>인원수</td>
                                <td>강의 상태</td>
                            </tr>
                        </thead>
                        <tbody>`;

            data.forEach(function(e, idx) {
                str += ` 
                    <tr>
                        <td>${idx + 1}</td>
                        <td class="classNum" name="classNum" value="${e.classNum}" onclick="changeClass(${e.classNum})">${e.className}</td>
                        <td>${e.stuCnt} / ${e.classPersonnel}</td>
                        <td>${e.teacherVO.strWork}</td>
                    </tr>`;
            });
            str +=  `</tbody>
                        </table>
                    </div>
                </div>
                <form action="/admin/changeAttendance" method="post">
                <div class="row">
                    <div class="col-2 text-center">재직상태</div>
                    <div class="col-10">
                        <div class="row text-center align-middle">
                            
                                <input type="hidden" name="teacherNum" value="${data[0].teacherVO.teacherNum}">
                                `;

            if(data[0].teacherVO.teacherWork == 1){
                                str += `
                                    <div class="col-4">
                                        <input class="form-check-input" type="radio" name="teacherWork" value="1" checked> 재직 
                                    </div>
                                    <div class="col-4">
                                        <input class="form-check-input" type="radio" name="teacherWork" value="2"> 퇴직 
                                    </div>`;
                                }
            else{
                str += `
                                    <div class="col-4">
                                        <input class="form-check-input" type="radio" name="teacherWork" value="1"> 재직 
                                    </div>
                                    <div class="col-4">
                                        <input class="form-check-input" type="radio" name="teacherWork" value="2" checked> 퇴직 
                                    </div>
                `;
            }
                               str += `
                                <div class="col-2 d-grid">
                                    <input class="btn btn-success" type="submit" value="변경">
                                </div>
                                <div class="col-2"></div>
                        </div>
                    </div>
                </div>
            </form>



            `;

        make_spot.insertAdjacentHTML("afterbegin", str);

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
    
    
}

function changeClass(classNum){

    if(classNum != 0){
        location.href=`/admin/goClassInfo?classNum=${classNum}`;
    }
    
}

function findTeacher(){
    const memberName = document.querySelector('.teacher-name').value;

   
    fetch('/admin/findTeacher', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
            memberName : memberName
        })
    })
    .then((response) => {
        // return response.text();
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!

        const selected_div = document.querySelector('.table-col-div');
        console.log(data);

        selected_div.innerHTML = '';
        let str = '';
        str += `
            <table class="table align-middle text-center">
                <colgroup>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="30%"/>
                    <col width="30%"/>
                </colgroup>
                <thead class="table-success">
                    <tr>
                        <td>No</td>
                        <td>반 이름</td>
                        <td>인원수</td>
                        <td>강의 상태</td>
                    </tr>
                </thead>
                <tbody>
                    `;
         
        data.forEach((e, i) => {
            str += `<tr>
                        <td>${i +1}</td>
                        <td class="classNum" name="classNum" value="${e.classNum}" onclick="changeClass(${e.classNum})">${e.className}</td>
                        <td>${e.stuCnt} / ${e.classPersonnel}</td>
                        <td>${e.teacherVO.strWork}</td>
                    </tr>`
        })
        str +=         `
                </tbody>
            </table>
            </div>
                </div>
                <form action="/admin/changeAttendance" method="post">
                <div class="row">
                    <div class="col-2 text-center">재직상태</div>
                    <div class="col-10">
                        <div class="row text-center align-middle">
                            
                                <input type="hidden" name="teacherNum" value="${data[0].teacherVO.teacherNum}">
        `;

        if(data[0].teacherVO.teacherWork == 1){
            str += `
                <div class="col-4">
                    <input class="form-check-input" type="radio" name="teacherWork" value="1" checked> 재직 
                </div>
                <div class="col-4">
                    <input class="form-check-input" type="radio" name="teacherWork" value="2"> 퇴직 
                </div>`;
            }
        else{
        str += `
                        <div class="col-4">
                            <input class="form-check-input" type="radio" name="teacherWork" value="1"> 재직 
                        </div>
                        <div class="col-4">
                            <input class="form-check-input" type="radio" name="teacherWork" value="2" checked> 퇴직 
                        </div>
        `;
        }
                str += `
                    <div class="col-2 d-grid">
                        <input class="btn btn-success" type="submit" value="변경">
                    </div>
                    <div class="col-2"></div>
            </div>
        </div>
        </div>
        </form>`;

        selected_div.insertAdjacentHTML("afterbegin", str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });


}



