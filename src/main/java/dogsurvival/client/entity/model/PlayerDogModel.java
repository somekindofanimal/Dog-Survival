package dogsurvival.client.entity.model;

import java.util.Optional;
import java.util.function.Function;

import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

public class PlayerDogModel<T extends Player> extends EntityModel<T> {

    public static final Vector3f DEFAULT_ROOT_PIVOT = new Vector3f(0, 15, 0);

    public ModelPart head;
    public ModelPart realHead;
    public ModelPart body;
    public ModelPart mane;
    public ModelPart legBackRight;
    public ModelPart legBackLeft;
    public ModelPart legFrontRight;
    public ModelPart legFrontLeft;
    public ModelPart tail;
    public ModelPart realTail;
    
    public ModelPart root;

    //Optional parts
    public Optional<ModelPart> earLeft;
    public Optional<ModelPart> earRight;

    public PlayerDogModel(ModelPart box) {
        this.root = box;
        this.head = box.getChild("head");
        this.realHead = this.head.getChild("real_head");
        this.body = box.getChild("body");
        this.mane = box.getChild("upper_body");
        this.legBackRight = box.getChild("right_hind_leg");
        this.legBackLeft = box.getChild("left_hind_leg");
        this.legFrontRight = box.getChild("right_front_leg");
        this.legFrontLeft = box.getChild("left_front_leg");
        this.tail = box.getChild("tail");
        this.realTail = this.tail.getChild("real_tail");

        this.addOptionalParts(box);
        this.correctInitalPose();
    }

    public PlayerDogModel(ModelPart box, Function<ResourceLocation, RenderType> renderType) {
        super(renderType);
        this.root = box;
        this.head = box.getChild("head");
        this.realHead = this.head.getChild("real_head");
        this.body = box.getChild("body");
        this.mane = box.getChild("upper_body");
        this.legBackRight = box.getChild("right_hind_leg");
        this.legBackLeft = box.getChild("left_hind_leg");
        this.legFrontRight = box.getChild("right_front_leg");
        this.legFrontLeft = box.getChild("left_front_leg");
        this.tail = box.getChild("tail");
        this.realTail = this.tail.getChild("real_tail");
        
        this.addOptionalParts(box);
        this.correctInitalPose();
    }

    protected void addOptionalParts(ModelPart box) {
        this.earLeft = getChildIfPresent(this.realHead, "left_ear");
        this.earRight = getChildIfPresent(this.realHead, "right_ear");
    }

    protected Optional<ModelPart> getChildIfPresent(ModelPart box, String name) {
        if (!box.hasChild(name))
            return Optional.empty();
        return Optional.of(box.getChild(name));
    }

    protected void correctInitalPose() {
        var tailPose = this.tail.getInitialPose();
        float tailX = tailPose.x, tailY = tailPose.y, tailZ = tailPose.z;
        this.tail.setInitialPose(PartPose.offset(tailX, tailY, tailZ));
    }

    public static LayerDefinition createBodyLayer() {
        var scale = CubeDeformation.NONE;
        MeshDefinition var0 = new MeshDefinition();
        PartDefinition var1 = var0.getRoot();
        float var2 = 13.5F;
        PartDefinition var3 = var1.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0F, 13.5F, -7.0F));
        var real_head = var3.addOrReplaceChild("real_head", CubeListBuilder.create()
                // Head
                .texOffs(0, 0).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, scale)
                // Nose
                .texOffs(0, 10).addBox(-1.5F, -0.001F, -5.0F, 3.0F, 3.0F, 4.0F, scale)
                , PartPose.ZERO);
        real_head.addOrReplaceChild("right_ear", CubeListBuilder.create()
            .texOffs(16, 14).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), 
                PartPose.offset(-2.0F, -3.0F, 0.5F));
        real_head.addOrReplaceChild("left_ear", CubeListBuilder.create()
            .texOffs(16, 14).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), 
                PartPose.offset(2.0F, -3.0F, 0.5F));
    
        var1.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(18, 14).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, scale)
        , PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        var1.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(21, 0).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, scale), PartPose.offsetAndRotation(0F, 14.0F, -3.0F, 1.5707964F, 0.0F, 0.0F));
        CubeListBuilder var4 = CubeListBuilder.create().texOffs(0, 18).addBox(-1F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, scale);
        var1.addOrReplaceChild("right_hind_leg", var4, PartPose.offset(-1.5F, 16.0F, 7.0F));
        var1.addOrReplaceChild("left_hind_leg", var4, PartPose.offset(1.5F, 16.0F, 7.0F));
        var1.addOrReplaceChild("right_front_leg", var4, PartPose.offset(-1.5F, 16.0F, -4.0F));
        var1.addOrReplaceChild("left_front_leg", var4, PartPose.offset(1.5F, 16.0F, -4.0F));
        PartDefinition var5 = var1.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(0F, 12.0F, 8.0F, 0.62831855F, 0.0F, 0.0F));
        var5.addOrReplaceChild("real_tail", CubeListBuilder.create()
                .texOffs(9, 18).addBox(-1F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, scale)
        , PartPose.ZERO);
        return LayerDefinition.create(var0, 64, 32);
    }

    @Override
    public void prepareMobModel(Player dog, float limbSwing, float limbSwingAmount, float partialTickTime) {
        this.resetAllPose();
        this.setUpStandPose(dog, limbSwing, limbSwingAmount, partialTickTime);
    }

    public void setUpStandPose(Player dog, float limbSwing, float limbSwingAmount, float partialTickTime) {
        animateStandWalking(dog, limbSwing, limbSwingAmount, partialTickTime);
    }

    public void animateStandWalking(Player dog, float limbSwing, float limbSwingAmount, float partialTickTime) {
        float w = Mth.cos(limbSwing * 0.6662F);
        float w1 = Mth.cos(limbSwing * 0.6662F + (float) Math.PI);
        float swing = Mth.clamp(limbSwingAmount, 0, 1);
        float modifier = 2.5f;
        this.body.xRot += getAnimateWalkingValue(w, swing, modifier * -5f*Mth.DEG_TO_RAD);
        this.body.y += getAnimateWalkingValue(w, swing, -0.25f*modifier);
        this.body.z +=  getAnimateWalkingValue(w, swing, -0.25f*modifier);

        this.mane.xRot += getAnimateWalkingValue(w, swing, modifier * 2.5f*Mth.DEG_TO_RAD );
        this.mane.y += getAnimateWalkingValue(w, swing, -0.25f*modifier);

        this.head.y += getAnimateWalkingValue(w, swing, -0.25f*modifier);

        this.tail.y += getAnimateWalkingValue(w, swing, 0.5f*modifier);
        this.tail.z += getAnimateWalkingValue(w, swing, -0.5f*modifier);

        if (this.earRight.isPresent()) {
            this.earRight.get().xRot += getAnimateWalkingValue(w, swing, -40f*Mth.DEG_TO_RAD );
            this.earRight.get().zRot += getAnimateWalkingValue(w, swing, -27.5f*Mth.DEG_TO_RAD );
            this.earRight.get().y += getAnimateWalkingValue(w, swing, 0.5f );
        }
        if (this.earLeft.isPresent()) {
            this.earLeft.get().xRot += getAnimateWalkingValue(w, swing, -40f*Mth.DEG_TO_RAD );
            this.earLeft.get().zRot += getAnimateWalkingValue(w, swing, 27.5f*Mth.DEG_TO_RAD );
            this.earLeft.get().y += getAnimateWalkingValue(w, swing, 0.5f );
        }

        this.legBackRight.xRot += w * 1.4F * limbSwingAmount;
        this.legBackLeft.xRot += w1 * 1.4F * limbSwingAmount;
        this.legFrontRight.xRot += w1 * 1.4F * limbSwingAmount;
        this.legFrontLeft.xRot += w * 1.4F * limbSwingAmount;
    }

    private float getAnimateWalkingValue(float w, float swingAmount, float amplitude) {
        int sign = Mth.sign(amplitude);
        amplitude = Math.abs(amplitude);
        return sign*Math.abs(amplitude * swingAmount * w);
    }

    public void resetAllPose() {
        this.root.getAllParts().forEach(x -> x.resetPose());
        this.realHead.resetPose();
        this.realTail.resetPose();
        this.earLeft.ifPresent(ear -> ear.resetPose());
        this.earRight.ifPresent(ear -> ear.resetPose());
    }


    @Override
    public void setupAnim(Player dog, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot += headPitch * ((float)Math.PI / 180F); 
        this.head.yRot += netHeadYaw * (float)Math.PI / 180F;
        this.tail.xRot = getTailRotation(dog);
        this.tail.yRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    private float getTailRotation(Player dog) {
        var max_h = dog.getMaxHealth();
        var radPerHealthDecrease = Mth.HALF_PI / max_h;
        return 
            (1.73f) - radPerHealthDecrease*(max_h - dog.getHealth());
    }

    @Override
    public void renderToBuffer(PoseStack p_102034_, VertexConsumer p_102035_, int p_102036_, int p_102037_, float p_102038_, float p_102039_, float p_102040_, float p_102041_) {
        // p_102038_ *= this.wetShade;
        // p_102039_ *= this.wetShade;
        // p_102040_ *= this.wetShade;
        
        var pivot = DEFAULT_ROOT_PIVOT;
        // var custom_pivot = getCustomRootPivotPoint();
        // if (custom_pivot != null) {
        //     pivot = custom_pivot;
        // }
        p_102034_.pushPose();
        p_102034_.translate((double)(root.x / 16.0F), (double)(root.y / 16.0F), (double)(root.z / 16.0F));
        p_102034_.translate((double)(pivot.x / 16.0F), (double)(pivot.y / 16.0F), (double)(pivot.z / 16.0F));
        if (root.zRot != 0.0F) {
            p_102034_.mulPose(Axis.ZP.rotation(root.zRot));
        }

        if (root.yRot != 0.0F) {
            p_102034_.mulPose(Axis.YP.rotation(root.yRot));
        }

        if (root.xRot != 0.0F) {
            p_102034_.mulPose(Axis.XP.rotation(root.xRot));
        }
        float xRot0 = root.xRot, yRot0 = root.yRot, zRot0 = root.zRot;
        float x0 = root.x, y0 = root.y, z0 = root.z;
        root.xRot = 0; root.yRot = 0; root.zRot = 0;
        root.x = 0; root.y = 0; root.z = 0;
        p_102034_.pushPose();
        p_102034_.translate((double)(-pivot.x / 16.0F), (double)(-pivot.y / 16.0F), (double)(-pivot.z / 16.0F));
        
        
        this.root.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
        

        p_102034_.popPose();
        p_102034_.popPose();
        root.xRot = xRot0; root.yRot = yRot0; root.zRot = zRot0;
        root.x = x0; root.y = y0; root.z = z0;
    }
    
}
