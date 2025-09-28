import formatterCAD from "../formatter"

export default function Settlements({debts}) {
    return (
        <div id="owings-card" className="card" style={{width: '50%'}}>
            <div className="card-header flex-row">
                <h2>Settlement Summary</h2>
            </div>
            <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '20px', marginTop: '20px'}}>
                <div>
                    {debts && debts.map(debt => (
                    <p>{debt.payer.name} pays {debt.payee.name}: <em>{formatterCAD.format(debt.amount)}</em></p>
                ))}
                </div>
                
                <button>Settle Debts</button>
            </div>
            
            
        </div>
    )
}