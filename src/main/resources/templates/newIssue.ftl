<#import "parts/common.ftl" as c>

<@c.page>
    <h2>Create a new issue</h2>
    <div class="form-group mt-3">
        <form action="/newIssue" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="issueName" class="form-control" placeholder="Issue name"/>
            </div>
            <div class="form-group">
                <input type="text" name="description" class="form-control" placeholder="Description"/>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Create</button>
            </div>
        </form>
    </div>
</@c.page>