const modal = new bootstrap.Modal("#staticBackdrop");
const modal_body = document.querySelector(".modal-body");

function homework_crystal(thishc){
    let is_sure = confirm("수정하시겠습니까?");
    const hc = thishc.parentElement.firstElementChild.value;
    const selectName = thishc.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.textContent;
    if(is_sure){
        const ChwNum = document.querySelector("#C_hwNum");
        ChwNum.querySelector('input').value = hc;
        fetch('/homework/modalChange', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                // 데이터명 : 데이터값
                "hwNum" : hc
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
                <strong>수강명</strong>
                </td>
                <td>
                <strong>과제명</strong>
                </td>
            </tr>
            <tr>
                <td>
                    <select name = "classNum" class="form-select" style="text-align: center; border: 1px solid rgb(23, 141, 156);">`;
                
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
                    <input type="text" name="hwName" class="form-control" style="text-align: center; border: 1px solid rgb(23, 141, 156);" value="${data.homeworkVO.hwName}">
                </td>
            </tr>
            <tr>
                <td>
                    <strong>시작일</strong>
                </td>
                <td>
                    <strong>종료일</strong>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="date" name="hwSdate" class="form-control" style="text-align: center; border: 1px solid rgb(23, 141, 156);" value="${data.homeworkVO.hwSdate}">
                </td>
                <td>
                    <input type="date" name="hwEdate" class="form-control" style="text-align: center; border: 1px solid rgb(23, 141, 156);" value="${data.homeworkVO.hwEdate}">
                </td>
            </tr>
        </table>
        <input type="hidden" name="hwNum" value="${data.homeworkVO.hwNum}">`;
            modal_body.insertAdjacentHTML('afterbegin',str)
            modal.show();
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
        
        
    }
}

function homework_delete(thishd){
    let is_sure = confirm("삭제하시겠습니까?")
    const hd = thishd.parentElement.firstElementChild.value
    if(is_sure){
        const DhwNum = document.querySelector("#D_hwNum");
        DhwNum.querySelector('input').value = hd;
        DhwNum.submit();
    }
}

function crystal(){
    document.querySelector("#buttonSubmit").submit()
}