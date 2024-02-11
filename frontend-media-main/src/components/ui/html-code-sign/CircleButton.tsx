
type CircleButtonProps = {
    className: string
    link: string
    image: string
    classImage?: string
}
const CircleButton = ({className,link,image,classImage}:CircleButtonProps) => {
    return (
        <a className={`${className} w-[48px] p-2 flex items-center  rounded-full border-primary bg-secondary border-2  mx-2 min-w-[48px] min-h-[48px] `} href={link} target={'_blank'}>
            <img src={image} className={`${classImage} w-[24px] h-[24px]`} alt= {'image'}/>
        </a>
    );
};

export default CircleButton;