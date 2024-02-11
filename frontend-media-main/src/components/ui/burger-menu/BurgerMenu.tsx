
const BurgerMenu = () => {
    return (
        <button className={'hidden w-[48px] p-2 flex-col items-center justify-center rounded-full border-primary bg-secondary border-2  mx-2 min-w-[48px] min-h-[48px] sm:flex'}>
             <span className={'w-[24px] h-[2px] bg-quinary mb-[0.27rem] rounded'}></span>
            <span className={'w-[24px] h-[2px] bg-quinary rounded'}></span>
            <span className={'w-[24px] h-[2px] bg-quinary mt-[0.27rem] rounded'}></span>
        </button>
    );
};

export default BurgerMenu;