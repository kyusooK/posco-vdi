$(document).ready(function(){
    var OPT = {
        Cols:[
            { Header: "운전자 사원 번호", Name: "driverEmpNo", Type: "Text", Width:80, RelWidth:1},
            { Header: "이름", Name: "name", Type: "Text", Width:80, RelWidth:1},
            { Header: "회사 코드", Name: "companyCode", Type: "Text", Width:80, RelWidth:1},
            { Header: "작업 코드", Name: "worksCode", Type: "Text", Width:80, RelWidth:1},
            { Header: "HR 접근 레벨", Name: "hrAccessLevel", Type: "Text", Width:80, RelWidth:1},
            { Header: "감독자 유형", Name: "supervisorType", Type: "Text", Width:80, RelWidth:1},
            { Header: "현재 직업", Name: "currentJob", Type: "Text", Width:80, RelWidth:1},
            { Header: "핸드폰", Name: "handPhone", Type: "Text", Width:80, RelWidth:1},
            { Header: "면허 번호", Name: "licenseNumber", Type: "Text", Width:80, RelWidth:1},
            { Header: "국가 식별자", Name: "nationalIdentifier", Type: "Text", Width:80, RelWidth:1},
            { Header: "자원 ID", Name: "resourceId", Type: "Text", Width:80, RelWidth:1},
            { Header: "차량 유형", Name: "carType", Type: "Text", Width:80, RelWidth:1},
            { Header: "퇴직 플래그", Name: "retirementFlag", Type: "Text", Width:80, RelWidth:1},
            { Header: "퇴직 날짜", Name: "retiredDate", Type: "Text", Width:80, RelWidth:1},
            { Header: "이미지", Name: "image", Type: "Text", Width:80, RelWidth:1},
            { Header: "설명", Name: "description", Type: "Text", Width:80, RelWidth:1},
            { Header: "마지막 업데이트 날짜", Name: "lastUpdateDate", Type: "Text", Width:80, RelWidth:1},
            { Header: "마지막 업데이트한 사람", Name: "lastUpdatedBy", Type: "Text", Width:80, RelWidth:1},
            { Header: "생성 날짜", Name: "creationDate", Type: "Text", Width:80, RelWidth:1},
            { Header: "생성한 사람", Name: "createdBy", Type: "Text", Width:80, RelWidth:1},
            { Header: "생성 객체 유형", Name: "createdObjectType", Type: "Text", Width:80, RelWidth:1},
            { Header: "생성 객체 ID", Name: "createdObjectId", Type: "Text", Width:80, RelWidth:1},
            { Header: "생성 프로그램 ID", Name: "createdProgramId", Type: "Text", Width:80, RelWidth:1},
            { Header: "생성 타임스탬프", Name: "creationTimestamp", Type: "Text", Width:80, RelWidth:1},
            { Header: "마지막 업데이트 객체 유형", Name: "lastUpdatedObjectType", Type: "Text", Width:80, RelWidth:1},
            { Header: "마지막 업데이트 객체 ID", Name: "lastUpdatedObjectId", Type: "Text", Width:80, RelWidth:1},
            { Header: "마지막 업데이트 프로그램 ID", Name: "lastUpdateProgramId", Type: "Text", Width:80, RelWidth:1},
            { Header: "마지막 업데이트 타임스탬프", Name: "lastUpdateTimestamp", Type: "Text", Width:80, RelWidth:1},
            { Header: "데이터 종료 상태", Name: "dataEndStatus", Type: "Text", Width:80, RelWidth:1},
            { Header: "데이터 종료 객체 유형", Name: "dataEndObjectType", Type: "Text", Width:80, RelWidth:1},
            { Header: "데이터 종료 객체 ID", Name: "dataEndObjectId", Type: "Text", Width:80, RelWidth:1},
            { Header: "데이터 종료 프로그램 ID", Name: "dataEndProgramId", Type: "Text", Width:80, RelWidth:1},
            { Header: "데이터 종료 타임스탬프", Name: "dataEndTimestamp", Type: "Text", Width:80, RelWidth:1},
       ]
   };

   IBSheet.create({
       id:"sheet",
       el:"sheet_DIV",
       options:OPT
   });
});

function retrieve(){
    fetch("/drivers", {
        method: 'GET',
        headers: {
            "Cache-Control": "no-cache",
            "Pragma": "no-cache",
            "Content-Type": "application/json"
        }
    }).then(res => {
        return res.json();
    }).then(json => {
        sheet.loadSearchData(json)
    }).catch(error => {
        console.error("에러", error);
    });
}

function addData(){
   sheet.addRow();
}

function deleteData(){
    sheet.deleteRow(sheet.getFocusedRow());
}

function save(){
    var rows = sheet.getSaveJson()?.data;

    for(var i=0; i<rows.length;i++){
        switch(rows[i].STATUS){
            case "Added":
                var saveRow = rows[i];
                $.ajax({
                    url: "/drivers",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(saveRow)
                });
                break;
            case "Changed":
                var rowObj = sheet.getRowById(rows[i].id);
                var changedData = JSON.parse(sheet.getChangedData(rowObj))["Changes"][0];
                var id = rows[i].seq;
                $.ajax({
                    url: `/drivers/${id}`,
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(changedData)
                });
                break;
            case "Deleted":
                var id = rows[i].seq;
                $.ajax({
                    url: `/drivers/${id}`,
                    method: "DELETE",
                });
                break;
        }     
    }           
}