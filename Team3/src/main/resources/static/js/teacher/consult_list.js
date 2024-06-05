const modal = new bootstrap.Modal("#staticBackdrop");
const modal_body = document.querySelector(".modal-body");


function crystal_consult(thishc){
    let is_sure = confirm("수정하시겠습니까?");
    const cc = thishc.parentElement.firstElementChild.value;
    const selectName = thishc.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.textContent;
    const stuName = thishc.parentElement.previousElementSibling.previousElementSibling.textContent;
    const cstDate = thishc.parentElement.previousElementSibling.textContent;
    let title = '상담-';
    title += selectName;
    title += '-';
    title += stuName;
    title += '-';
    title += cstDate;
    if(is_sure){
        const CcsNum = document.querySelector("#C_csNum");
        CcsNum.querySelector('input').value = cc;
        fetch('/consult/modalChange', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                // 데이터명 : 데이터값
                "consultNum" : cc
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
            modal_body.innerHTML = ``;
            let str = ``;
            str += `<table class="table" style="text-align: center;">
                    <colgroup>
                        <col width="50%">
                        <col width="50%">
                    </colgroup>
            <tr>
                <td>
                    <strong>소속</strong>
                </td>
                <td>
                    <strong>수강생명</strong>
                </td>
            </tr>
            <tr>
                <td>
                    <select name = "classNum" class="form-select" onchange="change()" id="class_select" style="text-align: center; border: 1px solid rgb(23, 141, 156);">`;
                
                    data.classList.forEach(function(e,idx){
                        str+=`<option value=${e.classNum} `
                        if(selectName == e.className){
                            str+=`selected`
                        }
                        str += `> ${e.clsVO.className}</option>`;
                        
                    });
                str+=`
                    </select>
                </td>
                <td>
                    <select name = "memberId" class="form-select" id="stu_select" style="border: 1px solid rgb(23, 141, 156);">
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                <strong>상담 일자</strong>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="date" name="consultDate" class="form-control" style="text-align: center; border: 1px solid rgb(23, 141, 156);" value="${data.consultVO.consultDate}">
                </td>
            </tr>
        </table>
        <input type="hidden" name="consultNum" value="${data.consultVO.consultNum}">
        <input type="hidden" name="beforeTitle" value="`;str+=title; str+=`">`;
            modal_body.insertAdjacentHTML('afterbegin',str)
            modal.show();
            change(stuName);
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
        
        
    }
}

function change(stuName){
    const class_select = document.querySelector("#class_select");
    const class_num = class_select.options[class_select.selectedIndex].value;
    fetch('/consult/changeStuOption', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
            classNum : class_num
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
    .then((data1) => {//data -> controller에서 리턴되는 데이터!
        const stu_select = document.querySelector('#stu_select');
        str1 = ``;
        stu_select.innerHTML = ``;
        for(let i of data1){
            str1+= `<option value="${i.memberId}"`;
                if(stuName == i.memberName){
                    str1+=`selected`
                }
            str1+=`>${i.memberName}</option>`
        }
        stu_select.insertAdjacentHTML("afterbegin",str1)
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

function delete_consult(thisdc){
    let is_sure = confirm("삭제하시겠습니까?")
    const selectName = thisdc.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.textContent;
    const stuName = thisdc.parentElement.previousElementSibling.previousElementSibling.textContent;
    const cstDate = thisdc.parentElement.previousElementSibling.textContent;
    let title = '상담-';
    title += selectName;
    title += '-';
    title += stuName;
    title += '-';
    title += cstDate;
    const dc = thisdc.parentElement.firstElementChild.value
    if(is_sure){
        const DcsNum = document.querySelector("#D_csNum");
        DcsNum.querySelector('#csn').value = dc;
        DcsNum.querySelector('#cst').value = title;
        DcsNum.submit();
    }
}

function crystal(){
    document.querySelector("#buttonSubmit").submit();
}

function save_consultContent(thisConsultContent){
    const selectName = thisConsultContent.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.textContent;
    const stuName = thisConsultContent.parentElement.previousElementSibling.previousElementSibling.textContent;
    if(confirm(selectName+" "+stuName+"의 상담내용을 작성하시겠습니까?")){
        const ccNum = thisConsultContent.parentElement.firstElementChild.value;
        location.href='/consult/addConsultContentForm?consultNum='+ccNum;
    }
}















