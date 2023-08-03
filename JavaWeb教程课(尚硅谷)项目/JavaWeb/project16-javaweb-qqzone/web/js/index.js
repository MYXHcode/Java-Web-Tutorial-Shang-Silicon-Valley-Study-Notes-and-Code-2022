function page(pageNo) {
    window.location.href = "/qqzone/index/" + pageNo;
}

function deleteQQZone(fid) {
    if (confirm("是否确认删除？")) {
        let frm = document.getElementById("frm");

        if (event && event.srcElement && event.srcElement.tagName === "INPUT") {
            let input = event.srcElement;

            frm.action = "/qqzone/delete/" + fid;
            frm.submit();
            event.preventDefault();
        }

        // window.location.href="/qqzone/delete?id="+id;
    }
}
