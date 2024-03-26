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
                <Link className="git_links" href={member.git_link}>{member.git_link}</Link>
                <Link className="links" href={member.lin_link}>{member.lin_link}</Link>
            </div>
        </section>
        </>
    );
};

export default MembersCard;