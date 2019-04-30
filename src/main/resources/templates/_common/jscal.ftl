<link type="text/css" rel="stylesheet" href="/static/common/JSCal/src/css/jscal2.css" />
<script src="/static/common/JSCal/src/js/jscal2.js"></script>
<script src="/static/common/JSCal/src/js/lang/cn_1.js"></script>
<script >
    function bindCalendar(objId, tipsId){
        return new Calendar({
            inputField: objId,
            showTime: true,
            dateFormat:"%Y-%m-%d",
            trigger: tipsId,
            onSelect: function(){
                this.hide();
            }
        });
    }
</script>