import Link from 'next/link';
import { Member } from '../interface';
import '../styles.css';

const MembersCard = (member: Member) => {
    return(
        <>
        <section className="flex member_section">
            <div>
                <p>{member.name}</p>
                <img className="member_profile_img" src={member.img} alt={member.name}/>
            </div>
            <div>
                <Link className="git_links" href={member.git_link}><p>{member.git_link}</p></Link>
                <Link className="lin_links" href={member.lin_link}><p>{member.lin_link}</p></Link>
            </div>
        </section>
        </>
    );
};

export default MembersCard;