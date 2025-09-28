import formatterCAD from "../formatter"

export default function MemberItem({id, name, balance}){
    return (
        <div className="member-item">
            <div className="member-info">
                <div className="member-avatar">{name[0]}</div>
                <p className="member-name">{name}</p>
                <div className="balance positive">{formatterCAD.format(balance)}</div>
            </div>

            <button type="button" className="action-btn"
                data-id={id}
                data-name={name}>
                <i className="fa fa-pencil"></i>
            </button>

            <form method="post" action="@{/members/delete}" >
                <input type="hidden" value={id} name="id" />
                <button className="action-btn" type="submit"><i className="fa fa-trash-o"></i></button>
            </form>
        </div>
    )
}