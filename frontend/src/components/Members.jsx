import MemberItem  from "./MemberItem"

export default function Members({members, balances}){
    const membersArr = Object.values(members)
    console.log("memebersArr", membersArr)
    console.log("balances", balances)
    
    return (
        <div id="members-card" className="card" style={{width: '50%'}}>
            <div className="card-header flex-row">
                <h2>Members</h2>
                <button className="btn-primary">Add Member</button>
            </div>
            <div className="members-list">
                {membersArr && membersArr.map(member => (
                    <MemberItem key={member.id} name={member.name} balance={balances[member.id]} />
                ))}
            </div>
        </div>
    )
}

                