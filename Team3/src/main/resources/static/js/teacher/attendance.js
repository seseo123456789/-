function btnControl(rBtn){
    const labels = document.querySelectorAll('.aaa');
    for(const label of labels){
        if(label.previousElementSibling.checked){
            
        }
        else{
            label.className = '';
            label.className = 'aaa btn btn-outline-secondary';
        }
    }

    const label_tag = rBtn.nextElementSibling;
    switch (rBtn.value) {
        case '1':
            label_tag.classList.add('btn-outline-success');
            break;
        case '2':
            label_tag.classList.add('btn-outline-danger');
            break;
        case '3':
            label_tag.classList.add('btn-outline-warning');
            break;
        case '4':
            label_tag.classList.add('btn-outline-secondary');
            break;
        case '5':
            label_tag.classList.add('btn-outline-info');
            break;
    }
}
const class_select = document.querySelector("#class_select");
document.querySelector('#currentDate').value = new Date().toISOString().substring(0, 10);
const currentDate = document.querySelector('#currentDate').value;
const showAttendanceList = document.querySelector("#showAttendanceList");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function change(){
    const class_num = class_select.options[class_select.selectedIndex].value;
    fetch('/learn/changeStuOption', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
            'classNum' : class_num
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
        const stu_select = document.querySelector('#stu_select');
        let str = ``;
        let str2 = ``;
        stu_select.innerHTML = ``;
        data.studentList.map(function(student, index_1){
            str+=`
                <tr>
                    <td>
                        <input type="hidden" name="memberId" value="${student.memberId}">
                        ${student.memberName}
                    </td>
                `;
                data.atdList.map(function(atd, index_2){
                    str+=`
                    <td>
                        <input type="radio" onclick="btnControl(this)" class="btn-check" name="atdtNum_${index_1}" id="${index_1+'_'+index_2}" autocomplete="off" value="${atd.atdtNum}" data-member-id="${student.memberId}">
                        <label class="aaa btn btn-outline-secondary" for="${index_1+'_'+index_2}">${atd.atdtName}</label>
                    </td>
                    `;
                })
            str+=`</tr>
                    `
        });
        if(!data.nowCheckAttendance){
            str+=`<tr>
                <td colspan="6" style="text-align: center;">
                    <input type="button" id="insertAttendance" class="btn btn-success" value="저장">
                </td>
            </tr>
            `;
        }
        else{
            str+=`<tr>
                <td colspan="6" style="text-align: center;">
                    오늘은 이미 저장을 하셨습니다.
                    <input type="hidden" id="insertAttendance" class="btn btn-success" value="저장">
                </td>
            </tr>
            `;
        }
        
        stu_select.insertAdjacentHTML("afterbegin", str);
        data.fullAttendanceList.map(function(atd,index_3){
            str2 +=`<tr>
            <td>${data.fullAttendanceList.length - index_3}</td>
            <td>${atd.memberName}</td>
            <td>${atd.check}</td>
            <td>${atd.absent}</td>
            <td>${atd.tardy}</td>
            <td>${atd.early}</td>
            <td>${atd.supple}</td>
            </tr>`;
        });
        showAttendanceList.innerHTML="";
        showAttendanceList.insertAdjacentHTML("afterbegin",str2)
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        let selectedRadioData = [];
        document.querySelectorAll('.btn-check').forEach(function(radio) {
            radio.addEventListener('click', function() {
                const atdtNum = this.value;
                const memberId = this.getAttribute('data-member-id');
                const classNum = class_num;

                const selectedInfo = {
                    atdtNum: atdtNum,
                    memberId: memberId,
                    classNum: classNum,
                    atdDate : currentDate
                };

                // 배열에 추가 또는 덮어쓰기
                let foundIndex = selectedRadioData.findIndex(item => item.memberId === memberId);
                if (foundIndex !== -1) {
                    // 이미 존재하는 경우 덮어쓰기
                    selectedRadioData[foundIndex] = selectedInfo;
                } else {
                    // 새로 추가
                    selectedRadioData.push(selectedInfo);
                }
                
            });
        });

        document.getElementById('insertAttendance').addEventListener('click', function() {
            // 여기서는 selectedRadioData 배열을 컨트롤러로 전송하는 fetch 요청을 보냅니다.
            if(confirm("한번 저장한 출석은 되돌릴 수 없습니다. 저장 하시겠습니까?")){
                fetch('/learn/insertAttendance', {
                    method: 'POST',
                    cache: 'no-cache',
                    headers: {
                        'Content-Type': 'application/json; charset=UTF-8'
                    },
                    body: JSON.stringify(selectedRadioData)
                })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('데이터 전송 실패');
                    }
                    location.reload(true);
                })
                .catch((error) => {
                    console.error('데이터 전송 에러:', error);
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
change();



